package com.project.franchise.domain.repository;

import com.project.franchise.domain.model.Franchise;

import java.util.Optional;

public interface FranchiseRepository {
    Franchise save(Franchise franchise);
    Optional<Franchise> findById(Long id);
    boolean existsByName(String name);
}
