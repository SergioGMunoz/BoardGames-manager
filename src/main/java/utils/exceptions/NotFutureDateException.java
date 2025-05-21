package utils.exceptions;

public class NotFutureDateException extends Exception {
    
    public NotFutureDateException() {
        super("La fecha no puede ser pasada");
    }
}
