package com.project.franchise.application.usecase.branch;

import com.project.franchise.application.dto.request.CreateBranchRequest;
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

    public void execute(CreateBranchRequest request) {
        franchiseRepository.findById(request.franchiseId())
                .orElseThrow(() -> new NotFoundException("Franchise not found"));

        Branch branch = new Branch(null, request.franchiseId(), request.name());
        branchRepository.save(branch);
    }
}
