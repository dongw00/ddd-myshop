package com.example.myshop.order.command.domain;

import com.example.myshop.member.command.domain.MemberId;

public interface OrdererService {

    Orderer createOrderer(MemberId ordererMemberId);
}
