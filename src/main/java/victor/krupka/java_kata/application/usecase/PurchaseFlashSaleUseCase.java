package victor.krupka.java_kata.application.usecase;

import victor.krupka.java_kata.domain.model.Customer;
import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.model.Purchase;
import victor.krupka.java_kata.domain.port.CustomerRepository;
import victor.krupka.java_kata.domain.port.FlashSaleRepository;
import victor.krupka.java_kata.domain.port.PurchaseRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class PurchaseFlashSaleUseCase {

    private final FlashSaleRepository flashSaleRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseFlashSaleUseCase(FlashSaleRepository flashSaleRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository) {
        this.flashSaleRepository = flashSaleRepository;
        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase execute(UUID flashSaleId, UUID customerId) {
        Instant now = Instant.now();
        FlashSale sale = flashSaleRepository.findById(flashSaleId)
                .orElseThrow(() -> new RuntimeException("FlashSale not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        sale.purchase(customer, now);

        flashSaleRepository.save(sale);
        Purchase purchase = new Purchase(UUID.randomUUID(), customerId, flashSaleId, now);
        purchaseRepository.save(purchase);

        return purchase;
    }
}
