package com.example.junit5.service;

import com.example.junit5.model.Invoice;
import com.example.junit5.model.InvoiceDTO;
import com.example.junit5.model.User;
import com.example.junit5.repository.InvoiceRepository;
import com.example.junit5.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class PurchaseService {

    private final InvoiceRepository invoiceRepository = new InvoiceRepository();
    private final UserRepository userRepository = new UserRepository();

    public BigDecimal getInvoicedAmount(User user, Invoice invoice) {

        return user.isPremium() ? invoice.amount().multiply(new BigDecimal("0.5")) : invoice.amount();
    }

    public BigDecimal getShippingAmount(User user, Invoice invoice) {

        if (user.isPremium() || !invoice.isRushDelivery()) return new BigDecimal("0.0");

        return invoice.amount().multiply(new BigDecimal("0.2"));
    }

    public List<InvoiceDTO> getFinalInvoicesByUser(int userId) {
        return invoiceRepository
                .getInvoicesByUserId(userId)
                .stream()
                .map(Invoice::id)
                .map(invoiceId -> getFinalInvoicedAmount(userId, invoiceId))
                .map(amount -> InvoiceDTO.of(amount, userId))
                .toList();
    }

    private BigDecimal getFinalInvoicedAmount(int userId, int invoiceId) {
        var user = userRepository.getUserById(userId).orElseThrow(()->new NoSuchElementException("User not found"));
        var invoice = invoiceRepository.getInvoiceById(invoiceId).orElseThrow();

        return getInvoicedAmount(user, invoice).add(getShippingAmount(user, invoice));
    }

}
