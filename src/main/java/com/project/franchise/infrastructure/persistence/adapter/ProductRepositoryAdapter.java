package com.project.franchise.infrastructure.persistence.adapter;

import com.project.franchise.domain.model.Product;
import com.project.franchise.domain.repository.ProductRepository;
import com.project.franchise.infrastructure.persistence.entity.BranchEntity;
import com.project.franchise.infrastructure.persistence.entity.ProductEntity;
import com.project.franchise.infrastructure.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository repo;

    public ProductRepositoryAdapter(JpaProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setStock(product.getStock());

        BranchEntity branch = new BranchEntity();
        branch.setId(product.getBranchId());
        entity.setBranch(branch);

        ProductEntity saved = repo.save(entity);

        return new Product(
                saved.getId(),
                saved.getBranch().getId(),
                saved.getName(),
                saved.getStock()
        );
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id)
            .map(e -> new Product(
                e.getId(),
                e.getBranch().getId(),
                e.getName(),
                e.getStock()
            ));
    }

    @Override
    public List<Product> findByBranchId(Long branchId) {
        return repo.findByBranchId(branchId)
            .stream()
            .map(e -> new Product(
                e.getId(),
                e.getBranch().getId(),
                e.getName(),
                e.getStock()
            ))
            .toList();
    }

    @Override
    public List<Product> findTopByFranchise(Long franchiseId) {
        return repo.findTopStockByFranchise(franchiseId)
            .stream()
            .map(e -> new Product(
                e.getId(),
                e.getBranch().getId(),
                e.getName(),
                e.getStock()
            ))
            .toList();
    }
}
