package com.project.franchise.infrastructure.persistence.adapter;

import com.project.franchise.domain.model.Franchise;
import com.project.franchise.domain.repository.FranchiseRepository;
import com.project.franchise.infrastructure.persistence.entity.FranchiseEntity;
import com.project.franchise.infrastructure.persistence.repository.JpaFranchiseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FranchiseRepositoryAdapter implements FranchiseRepository {
    private final JpaFranchiseRepository repo;

    public FranchiseRepositoryAdapter(JpaFranchiseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Franchise save(Franchise franchise) {
        FranchiseEntity entity = new FranchiseEntity();
        entity.setId(franchise.getId());
        entity.setName(franchise.getName());

        FranchiseEntity saved = repo.save(entity);

        return new Franchise(saved.getId(), franchise.getName());
    }

    @Override
    public Optional<Franchise> findById(Long id) {
        return repo.findById(id)
                .map(e -> new Franchise(e.getId(), e.getName()));
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }
}
