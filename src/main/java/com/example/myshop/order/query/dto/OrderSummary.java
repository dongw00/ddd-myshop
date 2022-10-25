package com.example.myshop.order.query.dto;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Getter
@Subselect(
    """
        SELECT o.order_number AS number,
        o.version,
        o.orderer_id,
        o.orderer_name,
        o.total_amounts,
        o.receiver_name,
        o.state,
        o.order_date,
        p.product_id,
        p.name AS product_name
        FROM purchase_order o INNER JOIN order_line ol
            ON o.order_number = ol.order_number
            CROSS JOIN product p
        WHERE
        ol.line_idx = 0
        AND ol.product_id = p.product_id
        """
)
@Synchronize({"purchase_order", "order_line", "product"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderSummary {

    @Id
    private String number;

    private long version;

    @Column(name = "orderer_id")
    private String ordererId;

    @Column(name = "orderer_name")
    private String ordererName;

    @Column(name = "total_amounts")
    private int totalAmounts;

    @Column(name = "receiver_name")
    private String receiverName;

    private String state;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;
}
