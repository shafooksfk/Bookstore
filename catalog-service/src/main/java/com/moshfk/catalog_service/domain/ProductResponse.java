package com.moshfk.catalog_service.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(String code, String name, String description, String imageUrl, BigDecimal price) {}
