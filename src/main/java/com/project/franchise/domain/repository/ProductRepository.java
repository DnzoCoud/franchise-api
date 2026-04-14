package com.project.franchise.domain.repository;

import com.project.franchise.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    void deleteById(Long id);
    Optional<Product> findById(Long id);
    List<Product> findByBranchId(Long branchId);
    List<Product> findTopByFranchise(Long franchiseId);
}
