package com.example.myshop.common.model;

import lombok.Getter;

@Getter
public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
