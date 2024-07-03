package com.example.junit5.repository;

import com.example.junit5.model.Invoice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository {
    List<Invoice> invoices = List.of(
            new Invoice(1, new BigDecimal("150.00"), 1, true),
            new Invoice(2, new BigDecimal("300.00"), 1, false),

            new Invoice(3, new BigDecimal("450.00"), 2, true),

            new Invoice(4, new BigDecimal("200.00"), 3, false),
            new Invoice(5, new BigDecimal("550.00"), 3, true),
            new Invoice(6, new BigDecimal("700.00"), 3, false),

            new Invoice(7, new BigDecimal("350.00"), 4, true),

            new Invoice(8, new BigDecimal("400.00"), 5, false),
            new Invoice(9, new BigDecimal("600.00"), 5, true),

            new Invoice(10, new BigDecimal("250.00"), 6, false),

            new Invoice(11, new BigDecimal("300.00"), 7, true),
            new Invoice(12, new BigDecimal("450.00"), 7, false),

            new Invoice(13, new BigDecimal("500.00"), 8, true),

            new Invoice(14, new BigDecimal("650.00"), 9, false),
            new Invoice(15, new BigDecimal("700.00"), 9, true),

            new Invoice(16, new BigDecimal("800.00"), 10, false),
            
            new Invoice(17, new BigDecimal("800.00"), 20, false)
    );

    public Optional<Invoice> getInvoiceById(int invoiceId) {
        return invoices.stream().filter(invoice -> invoice.id() == invoiceId).findFirst();
    }

    public List<Invoice> getRushDeliveryInvoices() {
        return invoices.stream().filter(Invoice::isRushDelivery).toList();
    }

    public List<Invoice> getInvoicesByUserId(int userId) {
        return invoices.stream().filter(invoice -> invoice.userId() == userId).toList();
    }
}
