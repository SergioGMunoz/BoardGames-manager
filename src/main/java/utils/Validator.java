package utils;

import utils.exceptions.*;

public class Validator {

    // Validador nombre 5-15 caracteres
    public static void validateName(String name) throws EmptyFieldException, FieldMinMaxCharactersException, SameFieldException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException();
        }
        
        int lenght = name.trim().length();
        if (lenght <= 0 || lenght > 15 ) {
            throw new FieldMinMaxCharactersException(5,15);
        }
        
        if (name.equals(Session.getName())) {
        	throw new SameFieldException();
        }
    }

    //Validador mail formato + caracteres 0-40
    public static void validateMail(String mail) throws EmptyFieldException, MailNotValidException, FieldMinMaxCharactersException, SameFieldException {
        if (mail == null || mail.trim().isEmpty()) {
            throw new EmptyFieldException();
        }

        if (!mail.toLowerCase().matches("^[a-z]+@[a-z]+\\.[a-z]+$")) {
            throw new MailNotValidException();
        }
        
        int lenght = mail.trim().length();
        if (lenght <= 0 || lenght > 40 ) {
            throw new FieldMinMaxCharactersException(5,40);
        }
        
        if (mail.equals(Session.getMail())) {
        	throw new SameFieldException();
        }
    }

    // Valida contraseña 4 - 15 caracteres
    public static void validatePassword(String password) throws EmptyFieldException, FieldMinMaxCharactersException, SameFieldException {
        if (password == null || password.trim().isEmpty()) {
            throw new EmptyFieldException();
        }

        int length = password.trim().length();
        if (length < 4 || length >= 16) {
            throw new FieldMinMaxCharactersException(4, 16);
        }
        
        String hashPassword = PasswordUtils.hashPassword(password);
        
        if (hashPassword.equals(Session.getPassword())) {
        	throw new SameFieldException();
        }
    }
}
