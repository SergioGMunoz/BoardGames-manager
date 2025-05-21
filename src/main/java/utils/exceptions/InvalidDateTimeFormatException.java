package utils.exceptions;

public class InvalidDateTimeFormatException extends Exception {

    public InvalidDateTimeFormatException() {
        super("El formato de fecha y hora no es válido");
    }
}
