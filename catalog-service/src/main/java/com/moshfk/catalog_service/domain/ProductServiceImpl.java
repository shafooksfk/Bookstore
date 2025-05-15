package com.moshfk.catalog_service.domain;

import com.moshfk.catalog_service.ApplicationProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    @Override
    public PagedResult<ProductResponse> getAllProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<ProductResponse> productsPage = productRepository.findAll(pageable).map(ProductMapper::toProductResponse);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }

    @Override
    public ProductResponse getProductByCode(String code) {
        return productRepository.findByCode(code)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
