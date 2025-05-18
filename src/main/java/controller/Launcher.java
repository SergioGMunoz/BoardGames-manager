package controller;

import model.ConnectionDB;
import view.LoginView;
import view.MainView;

public class Launcher {

	public static void main(String[] args) {
		
		// Iniciar BBDD
		if (ConnectionDB.connect("Java", "Hola12345", "localhost", "3306", "School_DB")) {
			
			// Iniciar vista login
			MainView mainView = new MainView();
			LoginView loginView = new LoginView();
			mainView.setContentPane(loginView);
			mainView.setVisible(true);
			
			System.out.println("✅ Proyecto listo y funcionando.");
		}
		
		
	}

}
