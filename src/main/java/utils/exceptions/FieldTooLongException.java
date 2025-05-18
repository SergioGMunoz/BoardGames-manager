package utils.exceptions;

public class FieldTooLongException extends Exception {

    public FieldTooLongException(int maxChars) {
        super(" supera el límite máximo de " + maxChars + " caracteres.");
    }
}
