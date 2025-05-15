package com.moshfk.catalog_service.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.moshfk.catalog_service.AbstractIntegrationTest;
import com.moshfk.catalog_service.domain.ProductResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIntegrationTest {

    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/product")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldReturnProductByCode_Success() {
        ProductResponse productResponse = given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/product/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductResponse.class);

        assertThat(productResponse.code()).isEqualTo("P100");
        assertThat(productResponse.name()).isEqualTo("The Hunger Games");
        assertThat(productResponse.description()).isEqualTo("Winning will make you famous, losing means certain death.");
        assertThat(productResponse.price()).isEqualTo(new BigDecimal("34.00"));
    }

    @Test
    void shouldReturnNotFoundWithInvalidProductCode_Failure() {
        String code = "invalid_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/product/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }
}
