package com.example.junit5.model;

import java.math.BigDecimal;

public record InvoiceDTO(BigDecimal amount, Integer userId) {

    public static InvoiceDTO of(BigDecimal amount, Integer userId) {
        return new InvoiceDTO(amount, userId);
    }
}
