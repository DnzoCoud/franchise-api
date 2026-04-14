package com.project.franchise.application.usecase.franchise;

import com.project.franchise.application.dto.response.FranchiseResponse;
import com.project.franchise.domain.repository.FranchiseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListAllFranchisesUseCase {
    private final FranchiseRepository franchiseRepository;

    public ListAllFranchisesUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public List<FranchiseResponse> execute() {
        var response = this.franchiseRepository.findAll();
        return response.stream().map(e -> new FranchiseResponse(e.getId(), e.getName())).collect(Collectors.toList());
    }
}
