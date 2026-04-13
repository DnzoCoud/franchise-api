package com.project.franchise.domain.repository;

import com.project.franchise.domain.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);
    List<Branch> findByFranchiseId(Long franchiseId);
}
