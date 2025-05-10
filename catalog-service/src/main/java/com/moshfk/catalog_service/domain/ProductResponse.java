package com.moshfk.catalog_service.domain;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductResponse(String code, String name, String description, String imageUrl, BigDecimal price) {}
