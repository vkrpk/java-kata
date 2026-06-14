package victor.krupka.java_kata.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import victor.krupka.java_kata.application.usecase.GetActiveFlashSalesUseCase;
import victor.krupka.java_kata.application.usecase.PurchaseFlashSaleUseCase;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryCustomerRepository;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryFlashSaleRepository;
import victor.krupka.java_kata.infrastructure.persistence.InMemoryPurchaseRepository;

@Configuration
public class ApplicationConfig {

    @Bean
    public InMemoryFlashSaleRepository flashSaleRepository() {
        return new InMemoryFlashSaleRepository();
    }

    @Bean
    public InMemoryCustomerRepository customerRepository() {
        return new InMemoryCustomerRepository();
    }

    @Bean
    public PurchaseFlashSaleUseCase purchaseFlashSaleUseCase(
            InMemoryFlashSaleRepository flashSaleRepository,
            InMemoryCustomerRepository customerRepository,
            InMemoryPurchaseRepository purchaseRepository) {
        return new PurchaseFlashSaleUseCase(flashSaleRepository, customerRepository, purchaseRepository);
    }

    @Bean
    public GetActiveFlashSalesUseCase getActiveFlashSalesUseCase(
            InMemoryFlashSaleRepository flashSaleRepository,
            InMemoryCustomerRepository customerRepository) {
        return new GetActiveFlashSalesUseCase(flashSaleRepository, customerRepository);
    }

    @Bean
    public InMemoryPurchaseRepository purchaseRepository(InMemoryCustomerRepository customerRepository) {
        return new InMemoryPurchaseRepository(customerRepository);
    }
}