package com.moshfk.catalog_service.web.controllers;

import com.moshfk.catalog_service.domain.PagedResult;
import com.moshfk.catalog_service.domain.ProductResponse;
import com.moshfk.catalog_service.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
class ProductController {

    private final ProductService productService;

    @GetMapping()
    PagedResult<ProductResponse> getAllProducts(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        return productService.getAllProducts(pageNo);
    }

    @GetMapping("/{code}")
    ProductResponse getProductByCode(@PathVariable("code") String code) {
        return productService.getProductByCode(code);
    }
}
