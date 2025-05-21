package controller;

import model.ConnectionDB;
import utils.Debugger;
import view.MainView;

public class Launcher {

	public static void main(String[] args) {
		
		// Establece si se imprimen mensajes de Debug
		Debugger.setDebug(false);
		
		// Iniciar BBDD
		if (ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db")) {
			
			// Instanciar controlladores 
			AuthController authController = new AuthController();
			authController.startLogin();
			
			Debugger.print("✅ Proyecto listo y funcionando.");
		}
		
		
	}

}
