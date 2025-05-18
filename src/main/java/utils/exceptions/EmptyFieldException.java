package utils.exceptions;

public class EmptyFieldException extends Exception {
	// Error campo vacio
    public EmptyFieldException() {
        super(" no puede estar vacío");
    }
}
