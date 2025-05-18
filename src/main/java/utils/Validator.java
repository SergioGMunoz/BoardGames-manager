package utils;

import utils.exceptions.*;

public class Validator {

	public static void fieldValidator(String input) throws EmptyFieldException, FieldTooLongException {
	    if (input == null || input.trim().isEmpty()) {
	        throw new EmptyFieldException();
	    }

	    if (input.trim().length() > 30) {
	        throw new FieldTooLongException(30);
	    }
	}
}
