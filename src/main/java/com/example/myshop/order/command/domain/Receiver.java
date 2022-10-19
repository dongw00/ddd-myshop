package com.example.myshop.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;
}
