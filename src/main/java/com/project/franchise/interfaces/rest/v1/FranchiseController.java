package com.project.franchise.interfaces.rest.v1;

import com.project.franchise.application.dto.request.franchise.CreateFranchiseRequest;
import com.project.franchise.application.dto.request.franchise.UpdateFranchiseRequest;
import com.project.franchise.application.dto.response.FranchiseResponse;
import com.project.franchise.application.usecase.franchise.CreateFranchiseUseCase;
import com.project.franchise.application.usecase.franchise.ListAllFranchisesUseCase;
import com.project.franchise.application.usecase.franchise.UpdateFranchiseUseCase;
import com.project.franchise.domain.model.Franchise;
import com.project.franchise.interfaces.handler.ApiResponse;
import com.project.franchise.interfaces.handler.ApiRoutes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.V1 + "/franchises")
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final UpdateFranchiseUseCase updateFranchiseUseCase;
    private final ListAllFranchisesUseCase listAllFranchisesUseCase;

    public FranchiseController(
            CreateFranchiseUseCase createFranchiseUseCase,
            UpdateFranchiseUseCase updateFranchiseUseCase,
            ListAllFranchisesUseCase listAllFranchisesUseCase
    ) {
        this.createFranchiseUseCase = createFranchiseUseCase;
        this.updateFranchiseUseCase = updateFranchiseUseCase;
        this.listAllFranchisesUseCase = listAllFranchisesUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FranchiseResponse>>> execute() {
        var response = this.listAllFranchisesUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody CreateFranchiseRequest request) {
        var response = this.createFranchiseUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable Long id, @RequestBody UpdateFranchiseRequest request) {
        var response = this.updateFranchiseUseCase.execute(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(response));
    }
}
