package com.example.myshop.member.command.application;

import com.example.myshop.member.command.domain.Member;
import com.example.myshop.member.command.domain.MemberId;
import com.example.myshop.member.command.domain.MemberRepository;
import com.example.myshop.member.command.exception.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlockMemberService {

    private final MemberRepository memberRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void block(String memberId) {
        Member member = memberRepository.findById(new MemberId(memberId))
            .orElseThrow(NoMemberException::new);
        
        member.block();
    }
}
