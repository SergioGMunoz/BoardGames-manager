package utils.exceptions;

public class ShopNotOpenException extends Exception {

    public ShopNotOpenException() {
        super("El horario de la tienda es de 10 a 22h");
    }
}
