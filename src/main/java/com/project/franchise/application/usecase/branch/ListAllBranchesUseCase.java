package com.project.franchise.application.usecase.branch;

import com.project.franchise.application.dto.response.BranchResponse;
import com.project.franchise.domain.model.Branch;
import com.project.franchise.domain.repository.BranchRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllBranchesUseCase {
    private final BranchRepository branchRepository;

    public ListAllBranchesUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchResponse> execute() {
        var response = this.branchRepository.findAll();
        return response.stream().map(e -> new BranchResponse(e.getId(), e.getFranchiseId(), e.getName())).collect(Collectors.toList());
    }
}
