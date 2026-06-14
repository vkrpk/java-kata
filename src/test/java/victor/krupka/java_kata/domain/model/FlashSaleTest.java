package victor.krupka.java_kata.domain.model;

import org.junit.jupiter.api.Test;
import victor.krupka.java_kata.domain.exception.FlashSaleNotActiveException;
import victor.krupka.java_kata.domain.exception.InsufficientStockException;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FlashSaleTest {
    private FlashSale createSale(int stock, Instant start, Instant end) {
        return new FlashSale(UUID.randomUUID(), "Product", stock, start, end, stock);
    }

    private Customer standardCustomer() {
        return new Customer(UUID.randomUUID(), MembershipLevel.STANDARD, "standard@test.com", 0);
    }

    private Customer premiumCustomer() {
        return new Customer(UUID.randomUUID(), MembershipLevel.GOLD, "gold@test.com", 5);
    }

    @Test
    void should_decrement_stock_when_standard_customer_purchases_during_active_sale() {
        Instant now = Instant.now();
        Instant start = now.minusSeconds(60);
        Instant end = now.plusSeconds(3600);
        FlashSale sale = createSale(10, start, end);
        Customer customer = standardCustomer();
        sale.purchase(customer, now);
        assertEquals(9, sale.getRemainingStock());
    }

    @Test
    void should_throw_when_stock_is_empty() {
        Instant now = Instant.now();
        Instant start = now.minusSeconds(60);
        Instant end = now.plusSeconds(3600);
        FlashSale sale = createSale(0, start, end);
        Customer customer = standardCustomer();
        assertThrows(InsufficientStockException.class, () -> sale.purchase(customer, now));
    }

    @Test
    void should_throw_when_standard_customer_purchases_before_start_time() {
        Instant now = Instant.now();
        Instant start = now.plusSeconds(60);
        Instant end = now.plusSeconds(3600);
        FlashSale sale = createSale(10, start, end);
        Customer customer = standardCustomer();
        assertThrows(FlashSaleNotActiveException.class, () -> sale.purchase(customer, now));
    }

    @Test
    void should_purchase_premium_customer_during_anticipated_premium_sale() {
        Instant now = Instant.now();
        Instant start = now.plusSeconds(180);  // dans 3 minutes
        Instant end = now.plusSeconds(3600);
        FlashSale sale = createSale(10, start, end);
        Customer customer = premiumCustomer();
        sale.purchase(customer, now);
        assertEquals(9, sale.getRemainingStock());
    }

    @Test
    void should_throw_when_standard_customer_purchases_during_anticipated_premium_sale() {
        Instant now = Instant.now();
        Instant start = now.plusSeconds(180);
        Instant end = now.plusSeconds(3600);
        FlashSale sale = createSale(10, start, end);
        Customer customer = standardCustomer();
        assertThrows(FlashSaleNotActiveException.class, () -> sale.purchase(customer, now));
    }
}