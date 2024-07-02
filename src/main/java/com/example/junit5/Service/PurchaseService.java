package com.example.junit5.Service;

import com.example.junit5.model.InvoiceDTO;
import com.example.junit5.repository.InvoiceRepository;
import com.example.junit5.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseService {

    private final InvoiceRepository invoiceRepository = new InvoiceRepository();
    private final UserRepository userRepository = new UserRepository();

    public BigDecimal getInvoicedAmount(int userId, int invoiceId) {

        var user = userRepository.getUserById(userId).orElseThrow();
        var invoice = invoiceRepository.getInvoiceById(invoiceId).orElseThrow();

        return user.isPremium() ? invoice.amount().multiply(new BigDecimal("0.5")) : invoice.amount();
    }

    public BigDecimal getShippingAmount(int userId, int invoiceId) {

        var user = userRepository.getUserById(userId).orElseThrow();
        var invoice = invoiceRepository.getInvoiceById(invoiceId).orElseThrow();

        if (user.isPremium() || !invoice.isRushDelivery()) return new BigDecimal("0.0");

        return invoice.amount().multiply(new BigDecimal("0.2"));
    }

    public List<InvoiceDTO> getFinalInvoicesByUser(int userId) {
        return invoiceRepository
                .getInvoicesByUserId(userId).stream()
                .map(invoice -> getInvoicedAmount(userId, invoice.id()).add(getShippingAmount(userId, invoice.id())))
                .map(amount -> InvoiceDTO.of(amount, userId))
                .toList();
    }

}
