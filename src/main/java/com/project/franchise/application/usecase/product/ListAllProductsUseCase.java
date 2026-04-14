package com.project.franchise.application.usecase.product;

import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.domain.repository.ProductRepository;

import java.util.List;

public class ListAllProductsUseCase {
    private final ProductRepository productRepository;

    public ListAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> execute() {

        return productRepository.findAll()
            .stream()
            .map(e -> new ProductResponse(
                    e.getId(),
                    e.getBranchId(),
                    e.getName(),
                    e.getStock()
            )).toList();
    }
}
