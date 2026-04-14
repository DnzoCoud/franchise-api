package com.project.franchise.infrastructure.persistence.adapter;

import com.project.franchise.domain.model.Franchise;
import com.project.franchise.domain.repository.FranchiseRepository;
import com.project.franchise.infrastructure.persistence.entity.FranchiseEntity;
import com.project.franchise.infrastructure.persistence.repository.JpaFranchiseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FranchiseRepositoryAdapter implements FranchiseRepository {
    private final JpaFranchiseRepository repo;

    public FranchiseRepositoryAdapter(JpaFranchiseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Franchise save(Franchise franchise) {

        FranchiseEntity entity;

        if (franchise.getId() != null) {
            entity = repo.findById(franchise.getId())
                    .orElseThrow(() -> new RuntimeException("Franchise not found"));
        } else {
            entity = new FranchiseEntity();
        }
        entity.setName(franchise.getName());

        FranchiseEntity saved = repo.save(entity);

        return new Franchise(saved.getId(), franchise.getName());
    }

    @Override
    public List<Franchise> findAll() {
        var entities = repo.findAll();
        return entities.stream().map(entity -> new Franchise(entity.getId(), entity.getName())).collect(Collectors.toList());
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

    @Override
    public boolean existsByNameAndIdNot(Long id, String name) {
        return repo.existsByNameAndIdNot(name, id);
    }
}
