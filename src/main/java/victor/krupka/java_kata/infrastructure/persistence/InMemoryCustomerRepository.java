package victor.krupka.java_kata.infrastructure.persistence;

import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.port.CustomerRepository;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<UUID, Customer> customers = new HashMap<>();

    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}
