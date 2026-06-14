package victor.krupka.java_kata.domain.port;

import victor.krupka.java_kata.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID id);
}
