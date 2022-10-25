package com.example.myshop.order.infra;

import com.example.myshop.member.command.domain.MemberId;
import com.example.myshop.member.query.MemberData;
import com.example.myshop.member.query.MemberQueryService;
import com.example.myshop.order.command.domain.Orderer;
import com.example.myshop.order.command.domain.OrdererService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdererServiceImpl implements OrdererService {

    private final MemberQueryService memberQueryService;

    @Override
    public Orderer createOrderer(MemberId ordererMemberId) {
        MemberData memberData = memberQueryService.getMemberData(ordererMemberId.getId());
        return new Orderer(MemberId.of(memberData.getId()), memberData.getName());
    }
}
