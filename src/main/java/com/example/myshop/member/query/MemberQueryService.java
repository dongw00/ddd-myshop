package com.example.myshop.member.query;

import com.example.myshop.member.command.exception.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberDataDao memberDataDao;

    public MemberData getMemberData(String memberId) {
        MemberData memberData = memberDataDao.findById(memberId);
        if (memberData == null) {
            throw new NoMemberException();
        }
        return memberData;
    }
}
