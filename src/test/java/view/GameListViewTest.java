package view;

import controller.GameController;
import model.ConnectionDB;
import utils.Debugger;

public class GameListViewTest {

	public static void main(String[] args) {
		// Establece si se imprimen mensajes de Debug
		Debugger.setDebug(true);
		
		// Iniciar BBDD
		if (ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db")) {
			
			// Instanciar controlladores 
			GameController gc = new GameController(false);
			gc.startGameList();

		}

	}

}
