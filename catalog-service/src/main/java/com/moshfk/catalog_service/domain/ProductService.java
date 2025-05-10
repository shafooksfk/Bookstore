package com.moshfk.catalog_service.domain;

public interface ProductService {

    PagedResult<ProductResponse> getAllProducts(int pageNo);
}
