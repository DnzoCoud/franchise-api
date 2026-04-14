package com.project.franchise.infrastructure.persistence.repository;

import com.project.franchise.infrastructure.persistence.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFranchiseRepository extends JpaRepository<FranchiseEntity, Long> {
    boolean existsByName(String name);
    Optional<FranchiseEntity> findByName(String name);
}
