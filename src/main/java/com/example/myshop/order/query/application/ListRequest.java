package com.example.myshop.order.query.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListRequest {

    private int page;
    private int size;
}
