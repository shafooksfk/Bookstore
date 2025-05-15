package com.moshfk.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"})
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void testFindAllProducts_Success() {
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    void testFindProductByCode_Success() {
        Product product = productRepository.findByCode("P100").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("The Hunger Games");
        assertThat(product.getDescription()).isEqualTo("Winning will make you famous, losing means certain death.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.00"));
    }

    @Test
    void testFindProductByInvalidCode_Failure() {
        assertThat(productRepository.findByCode("invalid_code")).isEmpty();
    }
}
