package com.bilal.store.repositories;

import com.bilal.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findProductsByCategory(Long categoryId);
}
