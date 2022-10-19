package com.example.myshop.member.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor
public class MemberId implements Serializable {

    @Column(name = "member_id")
    private String id;

    protected MemberId() {
    }
}
