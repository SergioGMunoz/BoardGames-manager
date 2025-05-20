package model;

import java.util.ArrayList;

import controller.AuthController;
import model.GameDAO;
import utils.Debugger;

public class GameDAOTest {

    public static void main(String[] args) {
		Debugger.setDebug(true);
		ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db");
		
        GameDAO dao = new GameDAO();

        // Caso 1: sin filtros
        Debugger.print("Caso 1: Sin filtros");
        imprimir(dao.getFilteredGames(null, null, null, null));

        // Caso 2: por nombre parcial
        Debugger.print("\nCaso 2: Nombre contiene 'Arnak'");
        imprimir(dao.getFilteredGames("Arnak", null, null, null));

        // Caso 3: por número exacto de jugadores
        Debugger.print("\nCaso 3: Para 1 jugador");
        imprimir(dao.getFilteredGames(null, 1, null, null));

        // Caso 4: por tipo de juego
        Debugger.print("\nCaso 4: Tipo = 'Estrategia'");
        imprimir(dao.getFilteredGames(null, null, "Estrategia", null));

        // Caso 5: por tipo y jugadores
        Debugger.print("\nCaso 5: Tipo = 'Cartas', jugadores = 8");
        imprimir(dao.getFilteredGames(null, 7, "Cartas", null));

        // Caso 6: orden por más jugados
        Debugger.print("\nCaso 6: Orden = 'Más jugados'");
        imprimir(dao.getFilteredGames(null, null, null, "Más jugados"));

        // Caso 7: todos los filtros juntos
        Debugger.print("\nCaso 7: Nombre contiene 'catan', tipo = 'Estrategia', jugadores = 4, orden = 'Duración asc'");
        imprimir(dao.getFilteredGames("catan", 4, "Estrategia", "Duración asc"));
        
        // Caso 8: Orden = 'Duración asc' + filtro jugadores
        Debugger.print("\nCaso 8: Jugadores = 2, orden = 'Duración asc'");
        imprimir(dao.getFilteredGames(null, 2, null, "Duración asc"));

        // Caso 9: Orden = 'Duración desc' + filtro tipo
        Debugger.print("\nCaso 9: Tipo = 'Estrategia', orden = 'Duración desc'");
        imprimir(dao.getFilteredGames(null, null, "Estrategia", "Duración desc"));

        // Caso 10: Orden = 'Edad asc' + nombre parcial
        Debugger.print("\nCaso 10: Nombre contiene 'Go', orden = 'Edad asc'");
        imprimir(dao.getFilteredGames("Go", null, null, "Edad asc"));

        // Caso 11: Orden = 'Edad desc' + tipo 'Infantil'
        Debugger.print("\nCaso 11: Tipo = 'Infantil', orden = 'Edad desc'");
        imprimir(dao.getFilteredGames(null, null, "Infantil", "Edad desc"));

        // Caso 12: Orden = 'Más jugados' + tipo 'Cartas' + jugadores = 4
        Debugger.print("\nCaso 12: Tipo = 'Estrategia', jugadores = 2, orden = 'Más jugados'");
        imprimir(dao.getFilteredGames(null, 2, "Estrategia", "Más jugados"));
    }

    private static void imprimir(ArrayList<Object[]> juegos) {
        for (Object[] juego : juegos) {
            Debugger.print(juego[0] + " | " + juego[1] + " | " + juego[2] + " | " +
                           juego[3] + "-" + juego[4] + " jugadores | " +
                           juego[5] + " | Edad mínima: " + juego[6] + " | Img: " + juego[7]);
        }
    }
}
