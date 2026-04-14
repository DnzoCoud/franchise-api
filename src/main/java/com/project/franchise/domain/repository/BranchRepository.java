package com.project.franchise.domain.repository;

import com.project.franchise.domain.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends BaseRepository<Branch> {
    List<Branch> findByFranchiseId(Long franchiseId);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(Long id, String name);
}
