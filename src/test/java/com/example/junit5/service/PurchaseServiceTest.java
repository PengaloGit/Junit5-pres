package com.example.junit5.service;

import com.example.junit5.model.Invoice;
import com.example.junit5.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Purchase Service Tests")
public class PurchaseServiceTest {

    @Nested
    @DisplayName("Invoiced Amount Tests")
    class InvoicedAmountTests {

        @Test
        @DisplayName("Should return half the invoiced amount when user is premium")
        void premiumUserHalfInvoicedAmount() throws InterruptedException {
            // given
            Thread.sleep(10000); //10seconds
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
        void nonPremiumUserFullInvoicedAmount() throws InterruptedException {
            // given
            Thread.sleep(10000); //10seconds
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


    @Nested
    @DisplayName("Checking out repeated tests feature in junit5")
    class RepeatedTests {

        @Test
        @DisplayName("testing coupon discount amount")
        @RepeatedTest(10)
        void repeatedTest() {
            // given
            PurchaseService purchaseService = new PurchaseService();
            double discountPercentage = Math.random();

            // when
            BigDecimal amount = purchaseService.getCouponDiscountAmount(
                    new Invoice(1, new BigDecimal("100.0"), 1, false),
                    new BigDecimal(discountPercentage));
            // then
            assertEquals(amount,
                    new BigDecimal(discountPercentage).multiply(new BigDecimal("100.0")),
                    "coupon discount amount should be equal to the discount percentage");
        }
    }

    @Nested
    @DisplayName("Checking out parameterized tests feature in junit5")
    class ParamsTests {

        @ParameterizedTest(name = "Scenario {index} : {3}, invoice amount: {0}, discount: {1}, expected discount amount(output): {2}")
        @CsvSource(delimiter = '|', textBlock = """
                #---------------------------------------------------------------------------------------------------
                # INVOICED_AMOUNT | DISCOUNT |   EXPECTED_DISCOUNTED_AMOUNT |             DESCRIPTION
                #---------------------------------------------------------------------------------------------------
                 100.0            | 0.2      | 20.00                        | 20% discount should be 20.00
                #----------------------------------------------------------------------------------------------------
                 100.0            | 0.6      | 50.00                        | 60% discount should be caped at 50.00
                #----------------------------------------------------------------------------------------------------
                 100.0            |-0.1      | 0.00                         | negative discount should be 0.00
                #----------------------------------------------------------------------------------------------------
                 100.0            |-0.2      | 0.00                         | pls work my job depends on it :'(
                #----------------------------------------------------------------------------------------------------
                """)

        //NB toutes les lignes commancantes par # sont des ignorées
        @DisplayName("Test valid discount scenarios")
        void testValidDiscount(BigDecimal invoiceAmount, BigDecimal discount, BigDecimal expectedDiscountAmount, String description) {
            // given
            PurchaseService purchaseService = new PurchaseService();
            Invoice invoice = new Invoice(1, invoiceAmount, 1, false);

            // when
            BigDecimal actualDiscountAmount = purchaseService.getCouponDiscountAmount(invoice, discount);

            // then
            assertEquals(0, expectedDiscountAmount.compareTo(actualDiscountAmount), "Expected: " + expectedDiscountAmount + " but was: " + actualDiscountAmount);
        }
    }

    @Nested
    class dynamic {
        @TestFactory
        Stream<DynamicNode> dynamicOrganizationTests() {
            return Stream.of(
                    dynamicContainer("Loukmane's tutorials Corp.", Stream.of(
                            dynamicContainer("HR Department", Stream.of(
                                    dynamicTest("HR has 5 employees", () -> assertEquals(5, getEmployeeCount("HR"))),
                                    dynamicTest("HR policy compliance", () -> assertTrue(isPolicyCompliant("HR")))
                            )),
                            dynamicContainer("IT Department", Stream.of(
                                    dynamicTest("IT has 10 employees", () -> assertEquals(10, getEmployeeCount("IT"))),
                                    dynamicTest("IT policy compliance", () -> assertTrue(isPolicyCompliant("IT"))),
                                    dynamicContainer("IT Sub-Department - Development", Stream.of(
                                            dynamicTest("Development has 6 employees", () -> assertEquals(6, getEmployeeCount("Development"))),
                                            dynamicTest("Development policy compliance", () -> assertTrue(isPolicyCompliant("Development")))
                                    )),
                                    dynamicContainer("IT Sub-Department - Operations", Stream.of(
                                            dynamicTest("Operations has 4 employees", () -> assertEquals(4, getEmployeeCount("Operations"))),
                                            dynamicTest("Operations policy compliance", () -> assertTrue(isPolicyCompliant("Operations")))
                                    ))
                            ))
                    ))
            );
        }

        private int getEmployeeCount(String department) {
            return switch (department) {
                case "HR" -> 5;
                case "IT" -> 10;
                case "Development" -> 6;
                case "Operations" -> 4;
                default -> 0;
            };
        }

        private boolean isPolicyCompliant(String department) {
            return true;
        }
    }


}
