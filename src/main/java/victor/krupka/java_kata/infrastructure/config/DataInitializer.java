package victor.krupka.java_kata.infrastructure.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.model.MembershipLevel;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryCustomerRepository;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryFlashSaleRepository;

import java.time.Instant;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(InMemoryCustomerRepository customerRepository,
                                      InMemoryFlashSaleRepository flashSaleRepository) {
        return args -> {
            customerRepository.save(new Customer(
                    UUID.fromString("00000000-0000-0000-0000-000000000001"),
                    MembershipLevel.GOLD, "alice@test.com", 12));

            customerRepository.save(new Customer(
                    UUID.fromString("00000000-0000-0000-0000-000000000002"),
                    MembershipLevel.STANDARD, "bob@test.com", 0));

            flashSaleRepository.save(new FlashSale(
                    UUID.fromString("00000000-0000-0000-0000-000000000010"),
                    "iPhone 15", 10,
                    Instant.now().minusSeconds(60),
                    Instant.now().plusSeconds(3600),
                    10));
        };
    }
}