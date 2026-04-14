package com.project.franchise.application.usecase.franchise;

import com.project.franchise.application.dto.request.franchise.UpdateFranchiseRequest;
import com.project.franchise.application.dto.response.FranchiseResponse;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Franchise;
import com.project.franchise.domain.repository.FranchiseRepository;

public class UpdateFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public UpdateFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public FranchiseResponse updateFranchise(Long id, UpdateFranchiseRequest request) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Franchise not found"));

        franchise.updateName(request.name());
        var response = franchiseRepository.save(franchise);

        return new FranchiseResponse(response.getId(), request.name());
    }
}
