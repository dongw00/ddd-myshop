package com.example.myshop.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Money {

    private int value;

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
