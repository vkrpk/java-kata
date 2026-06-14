package victor.krupka.java_kata.application.usecase;

import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.port.CustomerRepository;
import victor.krupka.java_kata.domain.port.FlashSaleRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class GetActiveFlashSalesUseCase {

    private final FlashSaleRepository flashSaleRepository;
    private final CustomerRepository customerRepository;

    public GetActiveFlashSalesUseCase(FlashSaleRepository flashSaleRepository, CustomerRepository customerRepository) {
        this.flashSaleRepository = flashSaleRepository;
        this.customerRepository = customerRepository;
    }

    public List<FlashSale> execute(UUID customerId) {
        Instant now = Instant.now();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<FlashSale> allActive = flashSaleRepository.findAllActive(now);

        if (customer.isPremium()) {
            return allActive.stream()
                    .filter(sale -> sale.isPremiumActive(now))
                    .toList();
        } else {
            return allActive.stream()
                    .filter(sale -> sale.isPubliclyActive(now))
                    .toList();
        }
    }
}
