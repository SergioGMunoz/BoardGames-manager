package utils.exceptions;

public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super(" no puede estar vacío.");
    }
}
