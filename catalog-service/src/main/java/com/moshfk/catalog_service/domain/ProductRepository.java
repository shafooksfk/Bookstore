package com.moshfk.catalog_service.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);
}
