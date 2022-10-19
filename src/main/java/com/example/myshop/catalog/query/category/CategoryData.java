package com.example.myshop.catalog.query.category;

import com.example.myshop.catalog.command.domain.category.CategoryId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryData {

    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;
}
