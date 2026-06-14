package victor.krupka.java_kata.domain.port;

import victor.krupka.java_kata.domain.model.FlashSale;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlashSaleRepository {
    Optional<FlashSale> findById(UUID id);
    FlashSale save(FlashSale flashSale);
    List<FlashSale> findAllActive(Instant now);
}
