package victor.krupka.java_kata.infrastructure.persistence;

import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.Purchase;
import victor.krupka.java_kata.domain.port.CustomerRepository;
import victor.krupka.java_kata.domain.port.PurchaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryPurchaseRepository implements PurchaseRepository {
    private final Map<UUID, Purchase> purchases = new HashMap<>();

    private final CustomerRepository customerRepository;

    public InMemoryPurchaseRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        purchases.put(purchase.getId(), purchase);

        customerRepository.findById(purchase.getCustomerId()).ifPresent(customer -> {
            Customer updated = new Customer(
                    customer.getId(),
                    customer.getMembershipLevel(),
                    customer.getEmail(),
                    customer.getPurchaseCount() + 1
            );
            customerRepository.save(updated);
        });

        return purchase;
    }
}
