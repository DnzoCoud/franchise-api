package com.project.franchise.interfaces.rest.v1;

import com.project.franchise.application.dto.request.product.AddProductRequest;
import com.project.franchise.application.dto.request.product.UpdateProductNameRequest;
import com.project.franchise.application.dto.request.product.UpdateProductStockRequest;
import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.application.dto.response.TopProductResponse;
import com.project.franchise.application.usecase.product.*;
import com.project.franchise.interfaces.handler.ApiResponse;
import com.project.franchise.interfaces.handler.ApiRoutes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.V1 + "/products")
public class ProductController {
    private final AddProductUseCase addUseCase;
    private final DeleteProductUseCase deleteUseCase;
    private final UpdateProductStockUseCase updateStockUseCase;
    private final UpdateProductNameUseCase updateNameUseCase;
    private final GetTopStockProductsUseCase topStockUseCase;
    private final ListAllProductsUseCase listAllProductsUseCase;

    public ProductController(
            AddProductUseCase addUseCase,
            DeleteProductUseCase deleteUseCase,
            UpdateProductStockUseCase updateStockUseCase,
            UpdateProductNameUseCase updateNameUseCase,
            GetTopStockProductsUseCase topStockUseCase,
            ListAllProductsUseCase listAllProductsUseCase
    ) {
        this.addUseCase = addUseCase;
        this.deleteUseCase = deleteUseCase;
        this.updateStockUseCase = updateStockUseCase;
        this.updateNameUseCase = updateNameUseCase;
        this.topStockUseCase = topStockUseCase;
        this.listAllProductsUseCase = listAllProductsUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<?>>> getTopProducts() {
        var res = listAllProductsUseCase.execute();
        return ResponseEntity.ok(ApiResponse.ok(res));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody AddProductRequest request) {
        var res = addUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(res));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<?>> updateStock(
            @PathVariable Long id,
            @RequestBody UpdateProductStockRequest request
    ) {
        var res = updateStockUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(res));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<ApiResponse<?>> updateName(
            @PathVariable Long id,
            @RequestBody UpdateProductNameRequest request
    ) {
        var res = updateNameUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(res));
    }

    @GetMapping("/top-stock/{franchiseId}")
    public ResponseEntity<ApiResponse<List<TopProductResponse>>> getTopStock(
            @PathVariable Long franchiseId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(topStockUseCase.execute(franchiseId)));
    }
}
