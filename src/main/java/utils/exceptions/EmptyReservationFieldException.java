package utils.exceptions;

public class EmptyReservationFieldException extends Exception {

    public EmptyReservationFieldException() {
        super("Debes completar todos los campos de la reserva");
    }
}
