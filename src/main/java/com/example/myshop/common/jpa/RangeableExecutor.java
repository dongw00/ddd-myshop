package com.example.myshop.common.jpa;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface RangeableExecutor<T> {

    List<T> getRange(Specification<T> spec, Rangeable rangeable);
}
