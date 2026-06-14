package victor.krupka.java_kata.domain.exception;

public class PremiumAccessRequiredException extends RuntimeException {
    public PremiumAccessRequiredException(String message) {
        super(message);
    }
}
