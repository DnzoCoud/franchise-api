package com.project.franchise.domain.model;

import com.project.franchise.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class Branch {
    private final Long id;
    private final Long franchiseId;
    private String name;

    public Branch(Long id, Long franchiseId, String name) {
        if (franchiseId == null) {
            throw new DomainException("Branch must belong to a franchise");
        }
        if (name == null || name.isBlank()) {
            throw new DomainException("Branch name cannot be empty");
        }

        this.id = id;
        this.franchiseId = franchiseId;
        this.name = name;
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainException("Branch name cannot be empty");
        }
        this.name = newName;
    }
}
