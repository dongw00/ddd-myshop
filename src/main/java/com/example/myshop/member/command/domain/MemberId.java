package com.example.myshop.member.command.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberId implements Serializable {

    @Column(name = "member_id")
    private String id;

    public static MemberId of(String id) {
        return new MemberId(id);
    }
}
