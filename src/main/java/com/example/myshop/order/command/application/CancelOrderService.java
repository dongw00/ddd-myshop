package com.example.myshop.order.command.application;

import com.example.myshop.order.command.domain.CancelPolicy;
import com.example.myshop.order.command.domain.Canceller;
import com.example.myshop.order.command.domain.Order;
import com.example.myshop.order.command.domain.OrderNo;
import com.example.myshop.order.command.domain.OrderRepository;
import com.example.myshop.order.command.exception.NoCancellablePermission;
import com.example.myshop.order.command.exception.NoOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelOrderService {

    private final OrderRepository orderRepository;
    private final CancelPolicy cancelPolicy;

    @Transactional
    public void cancel(OrderNo orderNo, Canceller canceller) {
        Order order = orderRepository.findById(orderNo)
            .orElseThrow(NoOrderException::new);

        if (!cancelPolicy.hasCancellationPermission(order, canceller)) {
            throw new NoCancellablePermission();
        }
        order.cancel();
    }
}
