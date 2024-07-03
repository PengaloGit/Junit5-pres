package com.example.junit5.service;

import com.example.junit5.model.Invoice;
import com.example.junit5.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseServiceTest {

    @Test
    void should_return_half_the_invoiced_amount_when_user_is_premium() {

        // given
        PurchaseService purchaseService = new PurchaseService();
        var user = new User(1, "loukmane@gmail.com", true);
        var invoice = new Invoice(1, new BigDecimal("100.0"), 1, false);

        // when
        var invoicedAmount = purchaseService.getInvoicedAmount(user, invoice);

        // then
        assertAll("invoicedAmount checks",
                () -> assertNotNull(invoicedAmount, () -> {
                    // Simulate calling an external API for message construction
                    // This operation is only performed if the assertion fails
                    return "invoicedAmount should never be null";
                }),
                () -> assertEquals(new BigDecimal("50.00"), invoicedAmount, "invoicedAmount should be half the invoice amount")
        );// should fail

    }

    @Test
    void should_return_the_invoiced_amount_when_user_is_not_premium() {

        // given
        PurchaseService purchaseService = new PurchaseService();
        var user = new User(1, "loukmane@gmail.com", false);
        var invoice = new Invoice(1, new BigDecimal("100.0"), 1, false);

        // when
        var invoicedAmount = purchaseService.getInvoicedAmount(user, invoice);

        // then
        assertNotNull(invoicedAmount, "invoicedAmount should never be null");
        assertEquals(new BigDecimal("100.0"), invoicedAmount, "invoicedAmount should be equal to the invoice amount");

    }

    @Test
    void should_return_zero_shipping_amount_when_user_is_premium() {

        // given
        PurchaseService purchaseService = new PurchaseService();
        var user = new User(1, "loukmane@gmail.com", true);
        var invoice = new Invoice(1, new BigDecimal("100.0"), 1, false);

        // when
        var shippingAmount = purchaseService.getShippingAmount(user, invoice);

        // then
        assertNotNull(shippingAmount, "shippingAmount should never be null");
        assertEquals(new BigDecimal("0.0"), shippingAmount, "shippingAmount should be zero");
    }


    @Test
    void should_return_zero_shipping_amount_when_invoice_is_not_rushed_delivery() {

        // given
        PurchaseService purchaseService = new PurchaseService();
        var user = new User(1, "loukmane@gmail.com", false);
        var invoice = new Invoice(1, new BigDecimal("100.0"), 1, false);

        // when
        var shippingAmount = purchaseService.getShippingAmount(user, invoice);

        // then
        assertNotNull(shippingAmount, "shippingAmount should never be null");
        assertEquals(new BigDecimal("0.0"), shippingAmount, "shippingAmount should be zero");
    }

    @Test
    void should_return_twenty_percent_of_the_invoiced_amount_as_shipping_amount_when_invoice_is_rushed_delivery_and_user_not_premium() {

        // given
        PurchaseService purchaseService = new PurchaseService();
        var user = new User(1, "loukmane@gmail.com", false);
        var invoice = new Invoice(1, new BigDecimal("100.0"), 1, true);

        // when
        var shippingAmount = purchaseService.getShippingAmount(user, invoice);

        // then
        assertNotNull(shippingAmount, "shippingAmount should never be null");
        assertEquals(new BigDecimal("20.00"), shippingAmount, "shippingAmount should be 20% of the invoice amount");
    }


    @Test
    void should_return_final_invoices_by_user() {

        // given
        PurchaseService purchaseService = new PurchaseService();

        // when
        var finalInvoices = purchaseService.getFinalInvoicesByUser(1);

        // then
        assertAll("finalInvoices",
                () -> assertNotNull(finalInvoices, "Final invoices should not be null"),//assertion1
                () -> assertEquals(2, finalInvoices.size(), "Final invoices should contain 2 items"),//assertion2
                () -> assertEquals(new BigDecimal("75.000"), finalInvoices.get(0).amount(), "First invoice amount should be 75.000"),//assertion3
                () -> assertEquals(new BigDecimal("150.000"), finalInvoices.get(1).amount(), "Second invoice amount should be 150.000")//assertion4
        );

        //happy path
        //everything is working as expected
    }

    @Test
    void should_throw_exception_when_user_not_found() {

        // given
        PurchaseService purchaseService = new PurchaseService();

        // when
        //user doesn't exist
        // then
        assertThrows(NoSuchElementException.class, () -> purchaseService.getFinalInvoicesByUser(20));// we can assert exceptions

        //edge case
        //user not found
    }
}