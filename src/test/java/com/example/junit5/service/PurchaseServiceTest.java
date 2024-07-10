package com.example.junit5.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.junit5.model.Invoice;
import com.example.junit5.model.User;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.math.BigDecimal;

@DisplayName("Purchase Service Tests")
public class PurchaseServiceTest {

    @Nested
    @DisplayName("Invoiced Amount Tests")
    class InvoicedAmountTests {

        @Test
        @DisplayName("Should return half the invoiced amount when user is premium")
        void premiumUserHalfInvoicedAmount() {
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
                        return "invoicedAmount should never be null";
                    }),
                    () -> assertEquals(new BigDecimal("50.00"), invoicedAmount, "invoicedAmount should be half the invoice amount")
            );
        }

        @Test
        @DisplayName("Should return the invoiced amount when user is not premium")
        void nonPremiumUserFullInvoicedAmount() {
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
    }

    @Nested
    @DisplayName("Shipping Amount Tests")
    class ShippingAmountTests {

        @Test
        @DisplayName("Should return zero shipping amount when user is premium")
        void premiumUserZeroShippingAmount() {
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
        @DisplayName("Should return zero shipping amount when invoice is not rushed delivery")
        void nonRushedDeliveryZeroShippingAmount() {
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
        @DisplayName("Should return 20% of the invoiced amount as shipping amount when invoice is rushed delivery and user is not premium")
        void nonPremiumUserRushedDeliveryShippingAmount() {
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

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class this_is_just_a_test {

        @Test
        void class_method_test() {
        }

        @Test
        @DisplayName("A display overrides the display name generator")
        void if_it_is_negative() {
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " with a well written ", generator = ReplaceUnderscores.class)
    class this_is_just_another_test {

        @Test
        void class_method_test() {
        }

        @Test
        @DisplayName("A display overrides the display name generator")
        void if_it_is_negative() {
        }

    }

    @Nested
    @DisplayName("Dependency injection in junit5")
    class DependencyInjectionTests {

        @Test
        @DisplayName("Checking out the dependency injection")
        void testDependencyInjection() {
        }
    }



}
