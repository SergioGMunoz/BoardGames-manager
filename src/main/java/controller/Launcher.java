package controller;

import model.ConnectionDB;
import view.MainView;

public class Launcher {

	public static void main(String[] args) {
		
		// Iniciar BBDD
		if (ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db")) {
			
			// Instanciar controlladores 
			AuthController authController = new AuthController(new MainView());
			authController.start();
			
			System.out.println("✅ Proyecto listo y funcionando.");
		}
		
		
	}

}
