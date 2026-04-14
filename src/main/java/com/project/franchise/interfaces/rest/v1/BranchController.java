package com.project.franchise.interfaces.rest.v1;

import com.project.franchise.application.dto.request.branch.CreateBranchRequest;
import com.project.franchise.application.dto.request.branch.UpdateBranchRequest;
import com.project.franchise.application.usecase.branch.CreateBranchUseCase;
import com.project.franchise.application.usecase.branch.ListAllBranchesUseCase;
import com.project.franchise.application.usecase.branch.UpdateBranchUseCase;
import com.project.franchise.interfaces.handler.ApiResponse;
import com.project.franchise.interfaces.handler.ApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.V1 + "/branches")
public class BranchController {
    private final CreateBranchUseCase createBranchUseCase;
    private final UpdateBranchUseCase updateBranchUseCase;
    private final ListAllBranchesUseCase listAllBranchesUseCase;

    public BranchController(CreateBranchUseCase createBranchUseCase, UpdateBranchUseCase updateBranchUseCase, ListAllBranchesUseCase listAllBranchesUseCase) {
        this.createBranchUseCase = createBranchUseCase;
        this.updateBranchUseCase = updateBranchUseCase;
        this.listAllBranchesUseCase = listAllBranchesUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<?>>> list() {
        var response = this.listAllBranchesUseCase.execute();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @Operation(
            summary = "Create a new branch",
            description = "Creates a new branch associated with a franchise"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Branch created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Franchise not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Branch already exists for this franchise")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(
            @RequestBody CreateBranchRequest request
    ) {
        var resp = this.createBranchUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(resp));
    }

    @Operation(
            summary = "Update branch name",
            description = "Updates the name of an existing branch"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Branch updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Branch not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Branch name already exists in this franchise")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable Long id,
            @RequestBody UpdateBranchRequest request
    ) {
        var resp = updateBranchUseCase.execute(id, request);
        return ResponseEntity.ok(ApiResponse.ok(resp));
    }
}
