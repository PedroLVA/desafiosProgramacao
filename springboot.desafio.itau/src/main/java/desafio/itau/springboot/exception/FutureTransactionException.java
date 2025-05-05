package desafio.itau.springboot.exception;

public class FutureTransactionException extends RuntimeException {
    public FutureTransactionException(String message) {
        super(message);
    }
}
