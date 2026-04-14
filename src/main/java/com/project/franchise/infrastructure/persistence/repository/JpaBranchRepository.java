package com.project.franchise.infrastructure.persistence.repository;

import com.project.franchise.infrastructure.persistence.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBranchRepository extends JpaRepository<BranchEntity, Long> {
    List<BranchEntity> findByFranchiseId(Long franchiseId);
}
