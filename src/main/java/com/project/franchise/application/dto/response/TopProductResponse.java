package com.project.franchise.application.dto.response;

public record TopProductResponse(
    Long productId,
    String productName,
    int stock,
    Long branchId,
    String branchName
) {}
