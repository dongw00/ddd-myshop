package com.example.myshop.order.query.dto;

import com.example.myshop.member.command.domain.MemberId;
import com.example.myshop.order.command.domain.OrderNo;
import com.example.myshop.order.command.domain.OrderState;
import lombok.Getter;

@Getter
public class OrderView {

    private final String number;
    private final OrderState orderState;
    private final String memberName;
    private final String memberId;
    private final String productName;

    public OrderView(OrderNo orderNo, OrderState orderState, String memberName, MemberId memberId, String productName) {
        this.number = orderNo.getNumber();
        this.orderState = orderState;
        this.memberName = memberName;
        this.memberId = memberId.getId();
        this.productName = productName;
    }
}
