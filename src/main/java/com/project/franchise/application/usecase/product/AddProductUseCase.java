package com.project.franchise.application.usecase.product;

import com.project.franchise.application.dto.request.product.AddProductRequest;
import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Product;
import com.project.franchise.domain.repository.BranchRepository;
import com.project.franchise.domain.repository.ProductRepository;

public class AddProductUseCase {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public AddProductUseCase(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    public ProductResponse execute(AddProductRequest request) {
        branchRepository.findById(request.branchId())
                .orElseThrow(() -> new NotFoundException("Branch not found"));

        Product product = new Product(null, request.branchId(), request.name(), request.stock());

        var response = productRepository.save(product);
        return new ProductResponse(response.getId(), response.getBranchId(), response.getName(), response.getStock());
    }
}
