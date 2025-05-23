package utils;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import utils.exceptions.EmptyFieldException;
import utils.exceptions.EmptyReservationFieldException;
import utils.exceptions.FieldMinMaxCharactersException;
import utils.exceptions.InvalidDateTimeFormatException;
import utils.exceptions.MailNotValidException;
import utils.exceptions.NotFutureDateException;
import utils.exceptions.SameFieldException;
import utils.exceptions.ShopNotOpenException;

public class Validator {

    // Validador nombre 5-15 caracteres
    public static void validateName(String name) throws EmptyFieldException, FieldMinMaxCharactersException, SameFieldException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException();
        }
        
        int lenght = name.trim().length();
        if (lenght < 4 || lenght > 15 ) {
            throw new FieldMinMaxCharactersException(4,15);
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
        if (lenght < 5 || lenght > 40 ) {
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
    
    // Validador de fecha y hora
    public static void validateDateTime(LocalDateTime dateTime)
            throws InvalidDateTimeFormatException, NotFutureDateException, ShopNotOpenException, EmptyReservationFieldException {

        if (dateTime == null) {
            throw new EmptyReservationFieldException();
        }

        // Fecha no puede ser pasada
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new NotFutureDateException();
        }

        // Validar hora dentro del horario tienda
        LocalTime time = dateTime.toLocalTime();
        
        if (time.isBefore(LocalTime.of(10, 0)) || time.isAfter(LocalTime.of(21, 59))) {
            throw new ShopNotOpenException();
        }

    }




}
