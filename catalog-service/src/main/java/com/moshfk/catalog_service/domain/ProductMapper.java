package com.moshfk.catalog_service.domain;

class ProductMapper {
    static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .build();
    }
}
