package victor.krupka.java_kata.infrastructure.persistence;

import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.port.FlashSaleRepository;

import java.time.Instant;
import java.util.*;

public class InMemoryFlashSaleRepository implements FlashSaleRepository {

    private final Map<UUID, FlashSale> store = new HashMap<>();

    @Override
    public Optional<FlashSale> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public FlashSale save(FlashSale flashSale) {
        store.put(flashSale.getId(), flashSale);
        return flashSale;
    }

    @Override
    public List<FlashSale> findAllActive(Instant now) {
        return store.values().stream()
                .filter(sale -> sale.isPremiumActive(now))
                .toList();
    }
}
