package com.moshfk.catalog_service.web.controllers;

import com.moshfk.catalog_service.domain.PagedResult;
import com.moshfk.catalog_service.domain.ProductResponse;
import com.moshfk.catalog_service.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
class ProductController {

    private final ProductService productService;

    @GetMapping()
    PagedResult<ProductResponse> getAllProducts(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        return productService.getAllProducts(pageNo);
    }
}
