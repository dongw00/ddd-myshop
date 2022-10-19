package com.example.myshop.order.command.domain;

import com.example.myshop.catalog.command.domain.product.ProductId;
import com.example.myshop.common.jpa.MoneyConverter;
import com.example.myshop.common.model.Money;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class OrderLine {

    @Embedded
    private ProductId productId;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "price")
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "amounts")
    private Money amounts;

    protected OrderLine() {
    }

    public OrderLine(ProductId productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return price.multiply(quantity);
    }
}
