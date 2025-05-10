package com.moshfk.catalog_service.domain;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


public interface ProductService {

    PagedResult<ProductResponse> getAllProducts(int pageNo);
}
