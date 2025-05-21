package model;

import java.util.ArrayList;
import utils.Debugger;

public class GameDAOTest {

    public static void main(String[] args) {
        Debugger.setDebug(true);
        ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db");

        GameDAO dao = new GameDAO();

        // Caso 1: sin filtros de reserva
        Debugger.print("Caso 1: Sin filtros de reserva");
        imprimir(dao.getFilteredGames(null, null, null, null, null, null));

        // Caso 2: Arnak reservado 2024-06-01 de 16:00 a 17:30 → buscar en 16:15
        Debugger.print("\nCaso 2: Arnak reservado (2024-06-01, 16:15:00)");
        imprimir(dao.getFilteredGames("Arnak", null, null, null, "2024-06-01", "16:15:00"));

        // Caso 3: Catan reservado (2024-06-01, 18:00–19:15) → buscar 18:30
        Debugger.print("\nCaso 3: Catan reservado (2024-06-01, 18:30:00)");
        imprimir(dao.getFilteredGames("Catan", null, null, null, "2024-06-01", "18:30:00"));

        // Caso 4: Código Secreto reservado (2024-06-04, 19:00–19:45) → tipo = 'Fiesta', hora = 19:10
        Debugger.print("\nCaso 4: Código Secreto (2024-06-04, 19:10:00) tipo = 'Fiesta'");
        imprimir(dao.getFilteredGames(null, null, "Fiesta", null, "2024-06-04", "19:10:00"));

        // Caso 5: Root reservado (2024-06-03, 17:00–18:30) → jugadores = 2, hora = 17:15
        Debugger.print("\nCaso 5: Root reservado (2024-06-03, 17:15:00) jugadores = 2");
        imprimir(dao.getFilteredGames(null, 2, null, null, "2024-06-03", "17:15:00"));
    }

    private static void imprimir(ArrayList<Object[]> juegos) {
        if (juegos.isEmpty()) {
            Debugger.print("Sin juegos disponibles para esa hora.");
        } else {
            for (Object[] juego : juegos) {
                Debugger.print(juego[0] + " | " + juego[1] + " | " + juego[2] + " | " +
                        juego[3] + "-" + juego[4] + " jugadores | " +
                        juego[5] + " | Edad mínima: " + juego[6] + " | Img: " + juego[7]);
            }
        }
    }
}
