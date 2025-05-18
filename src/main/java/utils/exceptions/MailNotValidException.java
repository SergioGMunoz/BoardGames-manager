package utils.exceptions;

public class MailNotValidException extends Exception {
	// No cumple el formato del mail
	 public MailNotValidException() {
	        super("debe tener formato \"mail@empresa.dominio\"");
	    }
}
