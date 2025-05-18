package utils.exceptions;

public class FieldMinMaxCharactersException extends Exception {
	// Error campo NO entre min y max caracteres
    public FieldMinMaxCharactersException(int min, int max) {
        super("debe tener entre " + min + " y " + max + " caracteres");
    }
}
