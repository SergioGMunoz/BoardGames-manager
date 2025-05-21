package model;

import utils.Debugger;

public class ReservationDAOTest {

    public static void main(String[] args) {
        Debugger.setDebug(true);
        ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db");

        ReservationDAO dao = new ReservationDAO();

        System.out.println("Caso 1: Catan - reserva exacta dentro del intervalo (2024-06-01, 18:30:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-01", "18:30:00")); // ✅ true

        System.out.println("Caso 2: Catan - justo antes del intervalo (2024-06-01, 17:59:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-01", "17:59:00")); // ❌ false

        System.out.println("Caso 3: Arnak - solapada (2024-06-01, 16:15:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-01", "16:15:00")); // ✅ true

        System.out.println("Caso 4: Código Secreto - fuera de la franja (2024-06-04, 18:30:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-04", "18:30:00")); // ❌ false

        System.out.println("Caso 5: Root - inicio exacto (2024-06-03, 17:00:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-03", "17:00:00")); // ✅ true

        System.out.println("Caso 6: Root - justo después (2024-06-03, 18:31:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-03", "18:31:00")); // ❌ false

        System.out.println("Caso 7: Catan otro día (2024-06-02, 20:30:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-02", "20:30:00")); // ✅ true

        System.out.println("Caso 8: Día sin reservas (2024-06-06, 18:00:00)");
        printResultado(dao.getUserReservationBusy(2, "2024-06-06", "18:00:00")); // ❌ false

        System.out.println("Caso 9: Usuario distinto (userId = 1) misma hora que Catan");
        printResultado(dao.getUserReservationBusy(1, "2024-06-01", "18:30:00")); // ❌ false
    }

    private static void printResultado(boolean ocupado) {
        System.out.println("¿Tiene reserva en ese momento? " + (ocupado ? "✅ Sí" : "❌ No"));
    }
}
