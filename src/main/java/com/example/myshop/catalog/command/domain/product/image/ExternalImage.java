package com.example.myshop.catalog.command.domain.product.image;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EI")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalImage extends Image {

    public ExternalImage(String path) {
        super(path);
    }

    @Override
    public String getUrl() {
        return getPath();
    }

    @Override
    public boolean hasThumbnail() {
        return false;
    }

    @Override
    public String getThumbnailUrl() {
        return null;
    }
}
