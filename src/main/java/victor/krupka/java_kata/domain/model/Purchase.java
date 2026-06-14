package victor.krupka.java_kata.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Purchase {
    private final UUID id;
    private final UUID customerId;
    private final UUID flashSaleId;
    private final Instant purchasedAt;

    public Purchase(UUID id, UUID customerId, UUID flashSaleId, Instant purchasedAt) {
        this.id = id;
        this.customerId = customerId;
        this.flashSaleId = flashSaleId;
        this.purchasedAt = purchasedAt;
    }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public UUID getFlashSaleId() { return flashSaleId; }
    public Instant getPurchasedAt() { return purchasedAt; }
}
