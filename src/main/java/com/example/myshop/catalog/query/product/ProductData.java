package com.example.myshop.catalog.query.product;

import com.example.myshop.catalog.command.domain.category.CategoryId;
import com.example.myshop.catalog.command.domain.product.ProductId;
import com.example.myshop.catalog.command.domain.product.image.Image;
import com.example.myshop.common.jpa.MoneyConverter;
import com.example.myshop.common.model.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductData {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private final List<Image> images = new ArrayList<>();
    
    @Getter
    @EmbeddedId
    private ProductId id;

    @ElementCollection
    @CollectionTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds;

    @Getter
    private String name;

    @Getter
    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Getter
    private String detail;

    public ProductData(ProductId id, String name, Money price, String detail, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.images.addAll(images);
    }

    public List<Image> getImages() {
        return Collections.unmodifiableList(images);
    }

    public String getFirstImageThumbnailPath() {
        if (images.isEmpty()) {
            return null;
        }
        return images.get(0).getThumbnailUrl();
    }
}
