package com.project.franchise.domain.model;

import com.project.franchise.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void shouldFailWhenStockIsNegative() {
        assertThrows(DomainException.class, () ->
                new Product(1L, 1L, "Producto", -1)
        );
    }

    @Test
    void shouldUpdateStockCorrectly() {
        Product product = new Product(1L, 1L, "Producto", 10);

        product.updateStock(20);

        assertEquals(20, product.getStock());
    }
}
