package com.project.franchise.infrastructure.config;

import com.project.franchise.application.usecase.branch.CreateBranchUseCase;
import com.project.franchise.application.usecase.branch.ListAllBranchesUseCase;
import com.project.franchise.application.usecase.branch.UpdateBranchUseCase;
import com.project.franchise.application.usecase.franchise.CreateFranchiseUseCase;
import com.project.franchise.application.usecase.franchise.ListAllFranchisesUseCase;
import com.project.franchise.application.usecase.franchise.UpdateFranchiseUseCase;
import com.project.franchise.application.usecase.product.*;
import com.project.franchise.domain.repository.BranchRepository;
import com.project.franchise.domain.repository.FranchiseRepository;
import com.project.franchise.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    // ===== Use cases =====
    @Bean
    public ListAllFranchisesUseCase listAllFranchisesUseCase(FranchiseRepository repo) {
        return new ListAllFranchisesUseCase(repo);
    }

    @Bean
    public CreateFranchiseUseCase createFranchiseUseCase(FranchiseRepository repo) {
        return new CreateFranchiseUseCase(repo);
    }

    @Bean
    public UpdateFranchiseUseCase updateFranchiseUseCase(FranchiseRepository repo) {
        return new UpdateFranchiseUseCase(repo);
    }

    @Bean
    public CreateBranchUseCase createBranchUseCase(
            BranchRepository branchRepo,
            FranchiseRepository franchiseRepo
    ) {
        return new CreateBranchUseCase(branchRepo, franchiseRepo);
    }

    @Bean
    public UpdateBranchUseCase updateBranchUseCase(BranchRepository repo) {
        return new UpdateBranchUseCase(repo);
    }

    @Bean
    public ListAllBranchesUseCase listAllBranchesUseCase(BranchRepository repo) {
        return new ListAllBranchesUseCase(repo);
    }

    @Bean
    public AddProductUseCase addProductUseCase(
            ProductRepository productRepo,
            BranchRepository branchRepo
    ) {
        return new AddProductUseCase(productRepo, branchRepo);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepository repo) {
        return new DeleteProductUseCase(repo);
    }

    @Bean
    public UpdateProductStockUseCase updateStockUseCase(ProductRepository repo) {
        return new UpdateProductStockUseCase(repo);
    }

    @Bean
    public UpdateProductNameUseCase updateNameUseCase(ProductRepository repo) {
        return new UpdateProductNameUseCase(repo);
    }

    @Bean
    public GetTopStockProductsUseCase topStockUseCase(ProductRepository repo) {
        return new GetTopStockProductsUseCase(repo);
    }

    @Bean
    public ListAllProductsUseCase listAllProductsUseCase(ProductRepository repo) {
        return new ListAllProductsUseCase(repo);
    }
}
