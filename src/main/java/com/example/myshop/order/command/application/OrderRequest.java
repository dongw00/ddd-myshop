package com.example.myshop.order.command.application;

import com.example.myshop.member.command.domain.MemberId;
import com.example.myshop.order.command.domain.ShippingInfo;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private List<OrderProduct> orderProducts;
    private MemberId ordererMemberId;
    private ShippingInfo shippingInfo;
}
