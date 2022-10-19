package com.example.myshop.order.command.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
public class OrderNo implements Serializable {

    @Column(name = "order_number")
    private String number;

    protected OrderNo() {

    }

    public OrderNo(String number) {
        this.number = number;
    }
}
