package com.example.myshop.common.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public class Rangeable {

    private int start;
    private int limit;
    private Sort sort;
}
