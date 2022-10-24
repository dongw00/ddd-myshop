package com.example.myshop.order.command.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoOrderProductException extends RuntimeException {

    private String productId;
}
