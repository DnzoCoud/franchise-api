package com.project.franchise.application.usecase.branch;

import com.project.franchise.application.dto.request.branch.CreateBranchRequest;
import com.project.franchise.application.dto.response.BranchResponse;
import com.project.franchise.domain.exception.DuplicateException;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Branch;
import com.project.franchise.domain.repository.BranchRepository;
import com.project.franchise.domain.repository.FranchiseRepository;

public class CreateBranchUseCase {
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    public CreateBranchUseCase(BranchRepository branchRepository, FranchiseRepository franchiseRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    public BranchResponse execute(CreateBranchRequest request) {
        franchiseRepository.findById(request.franchiseId())
                .orElseThrow(() -> new NotFoundException("Franchise not found"));

        if (branchRepository.existsByName(request.name())) {
            throw new DuplicateException("Branch already exists");
        }

        Branch branch = new Branch(null, request.franchiseId(), request.name());
        var response = branchRepository.save(branch);

        return new BranchResponse(response.getId(), response.getFranchiseId(), response.getName());
    }
}
