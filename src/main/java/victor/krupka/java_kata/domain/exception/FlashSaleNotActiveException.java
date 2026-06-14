package victor.krupka.java_kata.domain.exception;

public class FlashSaleNotActiveException extends RuntimeException {
    public FlashSaleNotActiveException(String message) {
        super(message);
    }
}