package victor.krupka.java_kata.infrastructure.web;

import org.springframework.web.bind.annotation.*;
import victor.krupka.java_kata.application.usecase.GetActiveFlashSalesUseCase;
import victor.krupka.java_kata.application.usecase.PurchaseFlashSaleUseCase;
import victor.krupka.java_kata.domain.model.FlashSale;
import victor.krupka.java_kata.domain.model.Purchase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flash-sales")
public class FlashSaleController {

    private final PurchaseFlashSaleUseCase purchaseFlashSaleUseCase;
    private final GetActiveFlashSalesUseCase getActiveFlashSalesUseCase;

    public FlashSaleController(PurchaseFlashSaleUseCase purchaseFlashSaleUseCase,
                               GetActiveFlashSalesUseCase getActiveFlashSalesUseCase) {
        this.purchaseFlashSaleUseCase = purchaseFlashSaleUseCase;
        this.getActiveFlashSalesUseCase = getActiveFlashSalesUseCase;
    }

    @GetMapping
    public List<FlashSale> getActive(@RequestParam UUID customerId) {
        return getActiveFlashSalesUseCase.execute(customerId);
    }

    @PostMapping("/{id}/purchase")
    public Purchase purchase(@PathVariable UUID id, @RequestBody PurchaseRequest request) {
        return purchaseFlashSaleUseCase.execute(id, request.customerId());
    }
}