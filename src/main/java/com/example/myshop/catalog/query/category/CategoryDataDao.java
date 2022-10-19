package com.example.myshop.catalog.query.category;

import com.example.myshop.catalog.command.domain.category.CategoryId;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryDataDao extends Repository<CategoryData, CategoryId> {

    Optional<CategoryData> findById(CategoryId id);

    List<CategoryData> findAll();
}
