package utils.exceptions;

public class SameFieldException extends Exception{

	public SameFieldException() {
		super("no puede ser igual al existente");
	}
}
