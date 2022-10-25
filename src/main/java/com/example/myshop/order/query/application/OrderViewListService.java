package com.example.myshop.order.query.application;

import com.example.myshop.order.query.dao.OrderSummaryDao;
import com.example.myshop.order.query.dto.OrderSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderViewListService {

    private final OrderSummaryDao orderSummaryDao;

    @Transactional
    public Page<OrderSummary> getList(ListRequest listReq) {
        PageRequest pageable = PageRequest.of(
            listReq.getPage(),
            listReq.getSize(),
            Sort.by(Sort.Direction.DESC, "number"));
        return orderSummaryDao.findAll(pageable);
    }
}
