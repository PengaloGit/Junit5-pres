package com.example.junit5.service;

import com.example.junit5.model.Invoice;
import com.example.junit5.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertNotNull(invoicedAmount, ()->{
            //calling an external api for the message construction or logging
            //very costly operation
            return "invoicedAmount should never be null";
        });// lambda expression
        assertEquals(new BigDecimal("50.00"), invoicedAmount, "invoicedAmount should be half the invoice amount");

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
}