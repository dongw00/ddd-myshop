package com.example.myshop.order.command.application;

import com.example.myshop.catalog.command.domain.product.Product;
import com.example.myshop.catalog.command.domain.product.ProductId;
import com.example.myshop.catalog.command.domain.product.ProductRepository;
import com.example.myshop.common.exception.ValidationError;
import com.example.myshop.common.exception.ValidationErrorException;
import com.example.myshop.order.command.domain.Order;
import com.example.myshop.order.command.domain.OrderLine;
import com.example.myshop.order.command.domain.OrderNo;
import com.example.myshop.order.command.domain.OrderRepository;
import com.example.myshop.order.command.domain.OrderService;
import com.example.myshop.order.command.domain.OrderState;
import com.example.myshop.order.command.domain.Orderer;
import com.example.myshop.order.command.exception.NoOrderProductException;
import com.example.myshop.order.command.validator.OrderRequestValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Transactional
    public OrderNo placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderProduct op : orderRequest.getOrderProducts()) {
            Optional<Product> productOpt = productRepository.findById(new ProductId(op.getProductId()));
            Product product = productOpt.orElseThrow(() -> new NoOrderProductException(op.getProductId()));
            orderLines.add(new OrderLine(product.getId(), product.getPrice(), op.getQuantity()));
        }

        OrderNo orderNo = orderRepository.nextOrderNo();
        Orderer orderer = orderService.createOrderer(orderRequest.getOrdererMemberId());

        Order order = new Order(orderNo, orderer, orderLines, orderRequest.getShippingInfo(),
            OrderState.PAYMENT_WAITING);
        orderRepository.save(order);
        return orderNo;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }

}
