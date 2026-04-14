package com.project.franchise.application.usecase.product;

import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.repository.ProductRepository;

public class DeleteProductUseCase {
    private final ProductRepository repository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public void execute(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Product not found"));

        repository.deleteById(id);
    }
}
