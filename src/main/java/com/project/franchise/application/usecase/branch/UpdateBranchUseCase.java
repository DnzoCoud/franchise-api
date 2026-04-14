package com.project.franchise.application.usecase.branch;

import com.project.franchise.application.dto.request.branch.UpdateBranchRequest;
import com.project.franchise.application.dto.response.BranchResponse;
import com.project.franchise.domain.exception.NotFoundException;
import com.project.franchise.domain.model.Branch;
import com.project.franchise.domain.repository.BranchRepository;

public class UpdateBranchUseCase {
    private final BranchRepository branchRepository;

    public UpdateBranchUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public BranchResponse updateBranch(Long id, UpdateBranchRequest request) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Branch not found"));

        branch.updateName(request.name());
        var response = branchRepository.save(branch);

        return new BranchResponse(response.getId(), response.getFranchiseId(), response.getName());
    }
}
