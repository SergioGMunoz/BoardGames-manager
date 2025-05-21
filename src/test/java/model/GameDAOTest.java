package model;

import java.util.ArrayList;
import utils.Debugger;

public class GameDAOTest {

    public static void main(String[] args) {
		Debugger.setDebug(true);
		ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db");
		
        GameDAO dao = new GameDAO();

        // Caso 1: sin filtros
        Debugger.print("Caso 1: Sin filtros");
        imprimir(dao.getFilteredGames(null, null, null, null, null));

        // Caso 2: por nombre parcial
        Debugger.print("\nCaso 2: Nombre contiene 'Arnak'");
        imprimir(dao.getFilteredGames("Arnak", null, null, null, null));

        // Caso 3: por número exacto de jugadores
        Debugger.print("\nCaso 3: Para 1 jugador");
        imprimir(dao.getFilteredGames(null, 1, null, null, null));

        // Caso 4: por tipo de juego
        Debugger.print("\nCaso 4: Tipo = 'Estrategia'");
        imprimir(dao.getFilteredGames(null, null, "Estrategia", null, null));

        // Caso 5: por tipo y jugadores
        Debugger.print("\nCaso 5: Tipo = 'Cartas', jugadores = 8");
        imprimir(dao.getFilteredGames(null, 7, "Cartas", null, null));

        // Caso 6: orden por más jugados
        Debugger.print("\nCaso 6: Orden = 'Más jugados'");
        imprimir(dao.getFilteredGames(null, null, null, "Más jugados",null));

        // Caso 7: todos los filtros juntos
        Debugger.print("\nCaso 7: Nombre contiene 'catan', tipo = 'Estrategia', jugadores = 4, orden = 'Duración asc'");
        imprimir(dao.getFilteredGames("catan", 4, "Estrategia", "Duración asc", null));
        
        // Caso 8: Orden = 'Duración asc' + filtro jugadores
        Debugger.print("\nCaso 8: Jugadores = 2, orden = 'Duración asc'");
        imprimir(dao.getFilteredGames(null, 2, null, "Duración asc", null));

        // Caso 9: Orden = 'Duración desc' + filtro tipo
        Debugger.print("\nCaso 9: Tipo = 'Estrategia', orden = 'Duración desc'");
        imprimir(dao.getFilteredGames(null, null, "Estrategia", "Duración desc",null));

        // Caso 10: Orden = 'Edad asc' + nombre parcial
        Debugger.print("\nCaso 10: Nombre contiene 'Go', orden = 'Edad asc'");
        imprimir(dao.getFilteredGames("Go", null, null, "Edad asc",null));

        // Caso 11: Orden = 'Edad desc' + tipo 'Infantil'
        Debugger.print("\nCaso 11: Tipo = 'Infantil', orden = 'Edad desc'");
        imprimir(dao.getFilteredGames(null, null, "Infantil", "Edad desc",null));

        // Caso 12: Orden = 'Más jugados' + tipo 'Cartas' + jugadores = 4
        Debugger.print("\nCaso 12: Tipo = 'Estrategia', jugadores = 2, orden = 'Más jugados'");
        imprimir(dao.getFilteredGames(null, 2, "Estrategia", "Más jugados",null));
        
        Debugger.print("");
        Debugger.print("❌❌❌❌❌❌CASOS CON RESERVAS❌❌❌❌❌❌❌❌");
        Debugger.print("");
        
     // Caso 13: Arnak reservado 1 junio 16:00–17:30 → buscar entre 15:00–16:30
        Debugger.print("\nCaso 13: Arnak reservado el 2024-06-01 de 16:00 a 17:30 → buscar solapado");
        imprimir(dao.getFilteredGames("Arnak", null, null, null, new String[]{"2024-06-01", "15:00:00", "16:30:00"}));

        // Caso 14: Catan reservado 1 junio 18:00–19:15 → buscar solapado 18:30–19:00
        Debugger.print("\nCaso 14: Catan reservado el 2024-06-01 de 18:00 a 19:15 → buscar solapado");
        imprimir(dao.getFilteredGames("Catan", null, null, null, new String[]{"2024-06-01", "18:30:00", "19:00:00"}));

        // Caso 15: Cthulhu reservado 2 junio 20:00–21:00 → buscar 19:30–20:30 (solapa parcialmente)
        Debugger.print("\nCaso 15: Catan reservado el 2024-06-02 de 20:00 a 21:00 → buscar solapado");
        imprimir(dao.getFilteredGames("Catan", null, null, null, new String[]{"2024-06-02", "19:30:00", "20:30:00"}));

        // Caso 16: Código Secreto reservado 4 junio 19:00–19:45 → filtro tipo = 'Fiesta' + franja solapada
        Debugger.print("\nCaso 16: Código Secreto reservado el 2024-06-04 de 19:00 a 19:45 → tipo = 'Fiesta'");
        imprimir(dao.getFilteredGames(null, null, "Fiesta", null, new String[]{"2024-06-04", "19:00:00", "19:30:00"}));

        // Caso 17: Root reservado 3 junio 17:00–18:30 → jugadores = 2, franja solapada
        Debugger.print("\nCaso 17: Root reservado el 2024-06-03 de 17:00 a 18:30 → jugadores = 2");
        imprimir(dao.getFilteredGames(null, 2, null, null, new String[]{"2024-06-03", "17:15:00", "18:00:00"}));


    }

    private static void imprimir(ArrayList<Object[]> juegos) {
        for (Object[] juego : juegos) {
            Debugger.print(juego[0] + " | " + juego[1] + " | " + juego[2] + " | " +
                           juego[3] + "-" + juego[4] + " jugadores | " +
                           juego[5] + " | Edad mínima: " + juego[6] + " | Img: " + juego[7]);
        }
    }
}
