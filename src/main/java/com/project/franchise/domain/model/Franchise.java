package com.project.franchise.domain.model;

import com.project.franchise.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class Franchise {
    private final Long id;
    private String name;

    public Franchise(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new DomainException("Franchise name cannot be empty");
        }
        this.id = id;
        this.name = name;
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainException("Franchise name cannot be empty");
        }
        this.name = newName;
    }
}
