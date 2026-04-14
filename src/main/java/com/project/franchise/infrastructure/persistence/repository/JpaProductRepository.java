package com.project.franchise.infrastructure.persistence.repository;

import com.project.franchise.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByBranchId(Long branchId);

    @Query("""
       SELECT p FROM ProductEntity p
       JOIN p.branch b
       JOIN b.franchise f
       WHERE f.id = :franchiseId
       AND p.stock = (
           SELECT MAX(p2.stock)
           FROM ProductEntity p2
           WHERE p2.branch.id = b.id
       )
    """)
    List<ProductEntity> findTopStockByFranchise(Long franchiseId);
}
