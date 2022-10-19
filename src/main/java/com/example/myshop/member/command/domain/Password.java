package com.example.myshop.member.command.domain;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
public class Password {

    @Column(name = "password")
    private String value;

    protected Password() {
    }

    public boolean match(String password) {
        return this.value.equals(password);
    }
}
