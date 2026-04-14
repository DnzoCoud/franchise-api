package com.project.franchise.interfaces.rest.v1;

import com.project.franchise.application.dto.request.product.AddProductRequest;
import com.project.franchise.application.dto.request.product.UpdateProductNameRequest;
import com.project.franchise.application.dto.request.product.UpdateProductStockRequest;
import com.project.franchise.application.dto.response.ProductResponse;
import com.project.franchise.application.dto.response.TopProductResponse;
import com.project.franchise.application.usecase.product.*;
import com.project.franchise.interfaces.handler.ApiResponse;
import com.project.franchise.interfaces.handler.ApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Create a new product",
            description = "Creates a new product within a specific branch with initial stock"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Branch not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Product already exists in this branch")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody AddProductRequest request) {
        var res = addUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(res));
    }

    @Operation(
            summary = "Delete a product",
            description = "Deletes a product by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update product stock",
            description = "Updates the stock of a product. Stock must be non-negative"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Stock updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid stock value"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<?>> updateStock(
            @PathVariable Long id,
            @RequestBody UpdateProductStockRequest request
    ) {
        var res = updateStockUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(res));
    }

    @Operation(
            summary = "Update product name",
            description = "Updates the name of a product"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Product name updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Product name already exists in the branch")
    })
    @PatchMapping("/{id}/name")
    public ResponseEntity<ApiResponse<?>> updateName(
            @PathVariable Long id,
            @RequestBody UpdateProductNameRequest request
    ) {
        var res = updateNameUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(res));
    }

    @Operation(
            summary = "Get top stock product per branch",
            description = "Returns the product with the highest stock for each branch of a given franchise. Each result includes the product and the branch it belongs to"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Top products retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    @GetMapping("/top-stock/{franchiseId}")
    public ResponseEntity<ApiResponse<List<TopProductResponse>>> getTopStock(
            @PathVariable Long franchiseId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(topStockUseCase.execute(franchiseId)));
    }
}
