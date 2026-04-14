package com.project.franchise.domain.repository;

import com.project.franchise.domain.model.Franchise;

public interface FranchiseRepository extends BaseRepository<Franchise> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(Long id, String name);
}
