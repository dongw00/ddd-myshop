package com.example.myshop.order.command.domain;

import com.example.myshop.member.command.domain.MemberId;

public interface OrderService {

    Orderer createOrderer(MemberId ordererMemberId);
}
