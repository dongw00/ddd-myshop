package com.example.myshop.catalog.command.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.myshop.catalog.command.domain.product.image.ExternalImage;
import com.example.myshop.catalog.command.domain.product.image.Image;
import com.example.myshop.catalog.command.domain.product.image.InternalImage;
import com.example.myshop.common.model.Money;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
    }

    @DisplayName("저장")
    @Test
    void save() {
        List<Image> images = new ArrayList<>();
        images.add(new ExternalImage("https://extern.image.com/some-img.png"));
        images.add(new InternalImage("internal-img.png"));

        String productId = "PRD-01";
        int price = 9000;

        Product product = new Product(
            ProductId.of(productId),
            "제품-01",
            new Money(price),
            "상세 내용",
            images
        );
        productRepository.save(product);

        SqlRowSet rsProd = jdbcTemplate.queryForRowSet("SELECT * FROM product WHERE product_id = ?", productId);
        assertThat(rsProd.next()).isTrue();
        assertThat(rsProd.getInt("price")).isEqualTo(price);

        SqlRowSet rsImg = jdbcTemplate.queryForRowSet("SELECT * FROM image WHERE product_id = ? ORDER BY list_idx",
            productId);
        assertThat(rsImg.next()).isTrue();
        assertThat(rsImg.getString("image_type")).isEqualTo("EI");
    }

    @DisplayName("이미지 수정")
    @Transactional
    @Test
    void updateImages() {
        jdbcTemplate.update("insert into product values (?,?,?,?)", "PROD-02", "PRD 2", 10000, "상세");
        jdbcTemplate.update("insert into image values (?,?,?,?,?,?)",
            1, "PROD-02", 0, "EI", "http://images.img/img.01.png", LocalDateTime.now());
        jdbcTemplate.update("insert into image values (?,?,?,?,?,?)",
            2, "PROD-02", 1, "EI", "http://images.img/img.02.png", LocalDateTime.now());

        Product product = productRepository.findById(ProductId.of("PROD-02")).get();
        product.changeImages(List.of(
            new InternalImage("/path01.png"),
            new InternalImage("/path02.png")
        ));
        productRepository.flush();
    }
}