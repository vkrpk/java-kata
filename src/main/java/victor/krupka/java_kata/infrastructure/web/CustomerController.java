package victor.krupka.java_kata.infrastructure.web;

import org.springframework.web.bind.annotation.*;
import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryCustomerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final InMemoryCustomerRepository customerRepository;

    public CustomerController(InMemoryCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}