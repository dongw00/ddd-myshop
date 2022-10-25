package com.example.myshop.order.ui;

import com.example.myshop.order.query.application.OrderDetail;
import com.example.myshop.order.query.application.OrderDetailService;
import com.example.myshop.order.query.dao.OrderSummaryDao;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyOrderController {

    private final OrderDetailService orderDetailService;
    private final OrderSummaryDao orderSummaryDao;

    @RequestMapping("/my/orders")
    public String orders(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("orders", orderSummaryDao.findByOrdererId(user.getUsername()));
        return "my/orders";
    }

    @RequestMapping("/my/orders/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderNo);
        if (orderDetail.isEmpty()) {
            return "my/noOrder";
        }

        if (orderDetail.get().getOrderer().getMemberId().getId().equals(user.getUsername())) {
            modelMap.addAttribute("order", orderDetail.get());
            return "my/orderDetail";
        }
        return "my/notYourOrder";
    }
}
