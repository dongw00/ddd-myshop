package com.example.myshop.order.query.application;

import com.example.myshop.order.command.domain.Order;
import com.example.myshop.order.command.domain.OrderState;
import com.example.myshop.order.command.domain.Orderer;
import com.example.myshop.order.command.domain.ShippingInfo;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderDetail {

    private final String number;
    private final Orderer orderer;
    private final ShippingInfo shippingInfo;
    private final OrderState state;
    private final int totalAmounts;
    private final boolean notYetShipped;
    private final long version;
    private final List<OrderLineDetail> orderLines;

    public OrderDetail(Order order, List<OrderLineDetail> orderLines) {
        this.orderLines = orderLines;
        this.number = order.getNumber().getNumber();
        this.version = order.getVersion();
        this.orderer = order.getOrderer();
        this.shippingInfo = order.getShippingInfo();
        this.state = order.getState();
        this.notYetShipped = order.isNotYetShipped();
        this.totalAmounts = order.getTotalAmounts().getValue();
    }

}
