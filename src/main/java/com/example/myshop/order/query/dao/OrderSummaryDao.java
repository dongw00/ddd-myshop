package com.example.myshop.order.query.dao;

import com.example.myshop.order.query.dto.OrderSummary;
import com.example.myshop.order.query.dto.OrderView;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface OrderSummaryDao extends Repository<OrderSummary, String> {

    List<OrderSummary> findByOrdererId(String ordererId);

    List<OrderSummary> findByOrdererId(String ordererId, Sort sort);

    List<OrderSummary> findByOrdererId(String ordererId, Pageable pageable);

    List<OrderSummary> findByOrdererIdOrderByNumberDesc(String ordererId);

    List<OrderSummary> findAll(Specification<OrderSummary> spec);

    List<OrderSummary> findAll(Specification<OrderSummary> spec, Sort sort);

    List<OrderSummary> findAll(Specification<OrderSummary> spec, Pageable pageable);

    Page<OrderSummary> findAll(Pageable pageable);

    @Query("""
            SELECT new com.example.myshop.order.query.dto.OrderView(
                o.number, o.state, m.name, m.id, p.name
            )
            FROM Order o JOIN o.orderLines ol, Member m, Product p
            WHERE o.orderer.memberId.id = :ordererId
            AND o.orderer.memberId.id = m.id
            AND index(ol) = 0
            AND ol.productId.id = p.id
            ORDER BY o.number.number DESC
        """)
    List<OrderView> findOrderView(String ordererId);

}
