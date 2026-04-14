package com.project.franchise.application.usecase.product;

import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.application.dto.response.TopProductResponse;
import com.project.franchise.domain.repository.ProductRepository;

import java.util.List;

public class GetTopStockProductsUseCase {
    private final ProductRepository productRepo;

    public GetTopStockProductsUseCase(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<TopProductResponse> execute(Long franchiseId) {
        return productRepo.findTopByFranchise(franchiseId)
            .stream()
            .map(p -> new TopProductResponse(
                    p.getId(),
                    p.getName(),
                    p.getStock(),
                    p.getBranchId(),
                    null
            ))
            .toList();
    }
}
