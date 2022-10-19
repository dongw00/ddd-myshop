package com.example.myshop.catalog.query.product;

import com.example.myshop.catalog.query.category.CategoryData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryProduct {

    private CategoryData category;
    private List<ProductSummary> items;
    private int page;
    private int size;
    private long totalCount;
    private int totalPages;
}
