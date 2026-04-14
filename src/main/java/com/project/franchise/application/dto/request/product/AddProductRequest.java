package com.project.franchise.application.dto.request.product;

public record AddProductRequest(Long branchId, String name, int stock) {
}
