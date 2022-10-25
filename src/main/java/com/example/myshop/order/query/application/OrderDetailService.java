package com.example.myshop.order.query.application;

import com.example.myshop.catalog.query.product.ProductData;
import com.example.myshop.catalog.query.product.ProductQueryService;
import com.example.myshop.order.command.domain.Order;
import com.example.myshop.order.command.domain.OrderNo;
import com.example.myshop.order.command.domain.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderRepository orderRepository;
    private final ProductQueryService productQueryService;

    @Transactional
    public Optional<OrderDetail> getOrderDetail(String orderNumber) {
        Optional<Order> orderOpt = orderRepository.findById(new OrderNo(orderNumber));
        return orderOpt.map(order -> {
            List<OrderLineDetail> orderLines = order.getOrderLines().stream()
                .map(orderLine -> {
                    Optional<ProductData> productOpt = productQueryService.getProduct(orderLine.getProductId().getId());
                    return new OrderLineDetail(orderLine, productOpt.orElse(null));
                }).collect(Collectors.toList());
            return new OrderDetail(order, orderLines);
        });
    }
}
