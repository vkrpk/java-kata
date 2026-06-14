package victor.krupka.java_kata.domain.model;

import java.time.Instant;
import java.util.UUID;

public class FlashSale {
    private final UUID id;
    private final String productName;
    private final int initialStock;
    private int remainingStock;
    private final Instant startTime;
    private final Instant endTime;

    public FlashSale(UUID id, String productName, int initialStock, Instant startTime, Instant endTime, int remainingStock) {
        this.id = id;
        this.productName = productName;
        this.initialStock = initialStock;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remainingStock = remainingStock;
    }

    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getInitialStock() {
        return initialStock;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void purchase(Customer customer, Instant now) {
        if (now.isAfter(endTime)) {
            throw new RuntimeException("Flash sale is over");
        }

        if (customer.isPremium()) {
            Instant premiumStart = startTime.minusSeconds(5 * 60);
            if (now.isBefore(premiumStart)) {
                throw new RuntimeException("Flash sale not yet open, even for premium");
            }
        } else {
            if (now.isBefore(startTime)) {
                throw new RuntimeException("Flash sale not yet open");
            }
        }

        if (remainingStock <= 0) {
            throw new RuntimeException("No stock remaining");
        }

        remainingStock--;
    }
}
