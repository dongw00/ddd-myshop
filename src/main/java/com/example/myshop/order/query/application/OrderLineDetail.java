package com.example.myshop.order.query.application;

import com.example.myshop.catalog.query.product.ProductData;
import com.example.myshop.order.command.domain.OrderLine;
import lombok.Getter;

@Getter
public class OrderLineDetail {

    private final String productId;
    private final int price;
    private final int quantity;
    private final int amounts;
    private final String productName;
    private final String productImagePath;

    public OrderLineDetail(OrderLine orderLine, ProductData product) {
        this.productId = orderLine.getProductId().getId();
        this.price = orderLine.getPrice().getValue();
        this.quantity = orderLine.getQuantity();
        this.amounts = orderLine.getAmounts().getValue();
        this.productName = product.getName();
        this.productImagePath = product.getFirstImageThumbnailPath();
    }

}
