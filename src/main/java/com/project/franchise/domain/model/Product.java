package com.project.franchise.domain.model;

import com.project.franchise.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class Product {
    private final Long id;
    private final Long branchId;
    private String name;
    private int stock;

    public Product(Long id, Long branchId, String name, int stock) {
        if (branchId == null) {
            throw new DomainException("Product must belong to a branch");
        }
        if (name == null || name.isBlank()) {
            throw new DomainException("Product name cannot be empty");
        }
        if (stock < 0) {
            throw new DomainException("Stock cannot be negative");
        }

        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.stock = stock;
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new DomainException("Product name cannot be empty");
        }
        this.name = newName;
    }

    public void updateStock(int newStock) {
        if (newStock < 0) {
            throw new DomainException("Stock cannot be negative");
        }
        this.stock = newStock;
    }
}
