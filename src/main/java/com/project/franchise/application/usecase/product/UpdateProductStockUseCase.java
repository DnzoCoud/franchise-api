package com.project.franchise.application.usecase.product;

import com.project.franchise.application.dto.request.product.UpdateProductStockRequest;
import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Product;
import com.project.franchise.domain.repository.ProductRepository;

public class UpdateProductStockUseCase {

    private final ProductRepository repo;

    public UpdateProductStockUseCase(ProductRepository productRepository) {
        this.repo = productRepository;
    }

    public ProductResponse execute(Long id, UpdateProductStockRequest request) {
        Product product = repo.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        product.updateStock(request.stock());
        var response = repo.save(product);

        return new ProductResponse(response.getId(), response.getBranchId(), response.getName(), response.getStock());
    }


}
