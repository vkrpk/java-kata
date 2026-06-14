package victor.krupka.java_kata.domain.port;

import victor.krupka.java_kata.domain.model.Purchase;

public interface PurchaseRepository {
    Purchase save(Purchase purchase);
}
