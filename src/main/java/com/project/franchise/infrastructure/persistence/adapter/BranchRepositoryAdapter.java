package com.project.franchise.infrastructure.persistence.adapter;

import com.project.franchise.domain.model.Branch;
import com.project.franchise.domain.repository.BranchRepository;
import com.project.franchise.infrastructure.persistence.entity.BranchEntity;
import com.project.franchise.infrastructure.persistence.entity.FranchiseEntity;
import com.project.franchise.infrastructure.persistence.repository.JpaBranchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BranchRepositoryAdapter implements BranchRepository {
    private final JpaBranchRepository repo;

    public BranchRepositoryAdapter(JpaBranchRepository repo) {
        this.repo = repo;
    }

    @Override
    public Branch save(Branch branch) {
        BranchEntity entity = new BranchEntity();
        entity.setId(branch.getId());
        entity.setName(branch.getName());

        FranchiseEntity franchise = new FranchiseEntity();
        franchise.setId(branch.getFranchiseId());
        entity.setFranchise(franchise);

        BranchEntity saved = repo.save(entity);

        return new Branch(
                saved.getId(),
                saved.getFranchise().getId(),
                saved.getName()
        );
    }

    @Override
    public List<Branch> findAll() {
        var entities = this.repo.findAll();
        return entities.stream().map(e -> new Branch(e.getId(), e.getFranchise().getId(), e.getName())).collect(Collectors.toList());
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return repo.findById(id)
            .map(e -> new Branch(
                e.getId(),
                e.getFranchise().getId(),
                e.getName()
            ));
    }

    @Override
    public List<Branch> findByFranchiseId(Long franchiseId) {
        return repo.findByFranchiseId(franchiseId)
            .stream()
            .map(e -> new Branch(
                    e.getId(),
                    e.getFranchise().getId(),
                    e.getName()
            ))
            .toList();
    }
}
