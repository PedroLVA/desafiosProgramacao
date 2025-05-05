package desafio.itau.springboot.exception;

public class ExpiredTransactionException extends RuntimeException {
    public ExpiredTransactionException(String message) {
        super(message);
    }

    public ExpiredTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
