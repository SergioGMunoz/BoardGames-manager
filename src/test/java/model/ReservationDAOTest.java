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

        System.out.println("Caso 5: Azul - empieza antes y termina dentro (2025-05-30, 14:30:00 → 15:30:00)");
        printResultado(dao.getUserReservationBusy(2, "2025-05-30", "14:30:00", "15:30:00")); // ✅ true

        System.out.println("Caso 6: Dixit - empieza justo al acabar Azul pero dura 1h (2025-05-30, 16:00:00 → 17:00:00)");
        printResultado(dao.getUserReservationBusy(2, "2025-05-30", "16:00:00", "17:00:00")); // ✅ true (se solapa con Dixit)

        System.out.println("Caso 7: 7 Wonders - empieza justo antes y dura 1h30 (2025-05-30, 17:30:00 → 19:00:00)");
        printResultado(dao.getUserReservationBusy(2, "2025-05-30", "17:30:00", "19:00:00")); // ✅ true (se solapa con 7 Wonders)

        System.out.println("Caso 8: Sushi Go - empieza 15:00 y termina 15:45");
        printResultado(dao.getUserReservationBusy(2, "2025-05-31", "15:00:00", "15:45:00")); // ✅ true (se solapa con Sushi Go)

        System.out.println("Caso 9: Carcassonne - empieza 18:30 y dura 1h");
        printResultado(dao.getUserReservationBusy(2, "2025-05-31", "18:30:00", "19:30:00")); // ✅ true (se solapa con Dixit Odyssey)

    }

    private static void printResultado(boolean ocupado) {
        System.out.println("¿Tiene reserva en ese momento? " + (ocupado ? "✅ Sí" : "❌ No"));
    }
}
