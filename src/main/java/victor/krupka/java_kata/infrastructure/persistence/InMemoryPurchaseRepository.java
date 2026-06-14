package victor.krupka.java_kata.infrastructure.persistence;

import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.Purchase;
import victor.krupka.java_kata.domain.port.PurchaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryPurchaseRepository implements PurchaseRepository {
    private final Map<UUID, Purchase> purchases = new HashMap<>();


    @Override
    public Purchase save(Purchase purchase) {
        purchases.put(purchase.getId(), purchase);
        return purchase;
    }
}
