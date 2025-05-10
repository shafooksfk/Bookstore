package com.moshfk.catalog_service.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        }
)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void testFindAllProducts_Success() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }
}