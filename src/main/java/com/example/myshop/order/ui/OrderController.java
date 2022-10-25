package com.example.myshop.order.ui;

import com.example.myshop.catalog.query.product.ProductData;
import com.example.myshop.catalog.query.product.ProductQueryService;
import com.example.myshop.common.exception.ValidationErrorException;
import com.example.myshop.member.command.domain.MemberId;
import com.example.myshop.order.command.application.OrderProduct;
import com.example.myshop.order.command.application.OrderRequest;
import com.example.myshop.order.command.application.PlaceOrderService;
import com.example.myshop.order.command.domain.OrderNo;
import com.example.myshop.order.command.exception.NoOrderProductException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ProductQueryService productQueryService;
    private final PlaceOrderService placeOrderService;

    @PostMapping("/orders/orderConfirm")
    public String orderConfirm(@ModelAttribute("orderReq") OrderRequest orderRequest,
        ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(MemberId.of(user.getUsername()));
        populateProductsAndTotalAmountsModel(orderRequest, modelMap);
        return "order/confirm";
    }

    private void populateProductsAndTotalAmountsModel(OrderRequest orderRequest,
        ModelMap modelMap) {
        List<ProductData> products = getProducts(orderRequest.getOrderProducts());
        modelMap.addAttribute("products", products);
        int totalAmounts = 0;

        for (int i = 0; i < orderRequest.getOrderProducts().size(); i++) {
            OrderProduct op = orderRequest.getOrderProducts().get(i);
            ProductData prod = products.get(i);
            totalAmounts += op.getQuantity() * prod.getPrice().getValue();
        }
        modelMap.addAttribute("totalAmounts", totalAmounts);
    }

    private List<ProductData> getProducts(List<OrderProduct> orderProducts) {
        List<ProductData> results = new ArrayList<>();
        for (OrderProduct op : orderProducts) {
            Optional<ProductData> productOpt = productQueryService.getProduct(op.getProductId());
            ProductData product = productOpt.orElseThrow(
                () -> new NoOrderProductException(op.getProductId()));
            results.add(product);
        }
        return results;
    }

    @PostMapping("/orders/order")
    public String order(@ModelAttribute("orderReq") OrderRequest orderRequest, BindingResult bindingResult,
        ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(MemberId.of(user.getUsername()));

        try {
            OrderNo orderNo = placeOrderService.placeOrder(orderRequest);
            modelMap.addAttribute("orderNo", orderNo.getNumber());
            return "order/orderComplete";
        } catch (ValidationErrorException e) {
            e.getErrors().forEach(err -> {
                if (err.hasName()) {
                    bindingResult.rejectValue(err.getName(), err.getCode());
                } else {
                    bindingResult.reject(err.getCode());
                }
            });

            populateProductsAndTotalAmountsModel(orderRequest, modelMap);
            return "order/confirm";
        }
    }

    @ExceptionHandler(NoOrderProductException.class)
    public String handleNoOrderProduct() {
        return "order/noProduct";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}