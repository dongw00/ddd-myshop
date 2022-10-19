package com.example.myshop.catalog.query.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSummary {

    private String id;
    private String name;
    private int price;
    private String image;
}
