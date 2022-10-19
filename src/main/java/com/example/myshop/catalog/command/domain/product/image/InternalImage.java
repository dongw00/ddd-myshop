package com.example.myshop.catalog.command.domain.product.image;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("II")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InternalImage extends Image {

    public InternalImage(String path) {
        super(path);
    }

    @Override
    public String getUrl() {
        return "/images/original/" + getPath();
    }

    @Override
    public boolean hasThumbnail() {
        return true;
    }

    @Override
    public String getThumbnailUrl() {
        return "/images/thumbnail/" + getPath();
    }
}
