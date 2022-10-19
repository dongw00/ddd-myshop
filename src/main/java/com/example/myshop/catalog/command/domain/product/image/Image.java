package com.example.myshop.catalog.command.domain.product.image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
@Table(name = "image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Getter
    @Column(name = "image_path")
    private String path;

    @Getter
    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    public Image(String path) {
        this.path = path;
        this.uploadTime = LocalDateTime.now();
    }

    public abstract String getUrl();

    public abstract boolean hasThumbnail();

    public abstract String getThumbnailUrl();
}
