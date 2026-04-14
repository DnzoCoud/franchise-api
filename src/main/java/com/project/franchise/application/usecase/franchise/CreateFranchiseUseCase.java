package com.project.franchise.application.usecase.franchise;

import com.project.franchise.application.dto.request.franchise.CreateFranchiseRequest;
import com.project.franchise.application.dto.response.FranchiseResponse;
import com.project.franchise.domain.exception.DuplicateException;
import com.project.franchise.domain.model.Franchise;
import com.project.franchise.domain.repository.FranchiseRepository;

public class CreateFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public CreateFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public FranchiseResponse execute(CreateFranchiseRequest request) {
        if (franchiseRepository.existsByName(request.name())) {
            throw new DuplicateException("Franchise already exists");
        }

        Franchise franchise = new Franchise(null, request.name());
        Franchise saved = franchiseRepository.save(franchise);

        return new FranchiseResponse(saved.getId(), request.name());
    }
}
