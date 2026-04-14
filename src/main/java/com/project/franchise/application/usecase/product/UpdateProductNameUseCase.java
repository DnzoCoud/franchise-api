package com.project.franchise.application.usecase.product;

import com.project.franchise.application.dto.request.product.UpdateProductNameRequest;
import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Product;
import com.project.franchise.domain.repository.ProductRepository;

public class UpdateProductNameUseCase {
    private final ProductRepository productRepository;

    public UpdateProductNameUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse execute(Long id, UpdateProductNameRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        product.updateName(request.name());
        var response = productRepository.save(product);
        return new ProductResponse(response.getId(), response.getBranchId(), response.getName(), response.getStock());
    }
}
