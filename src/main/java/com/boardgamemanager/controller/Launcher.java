package com.boardgamemanager.controller;

import java.util.ArrayList;

import com.boardgamemanager.model.ConectionDB;
import com.boardgamemanager.view.GamesListView;
import com.boardgamemanager.view.LoginView;
import com.boardgamemanager.view.MainView;

public class Launcher {

	public static void main(String[] args) {
		
		// Iniciar BBDD
		if (ConectionDB.connect("Java", "Hola12345", "localhost", "3306", "School_DB")) {
			
			// Iniciar vista login
			MainView mainView = new MainView();
			LoginView loginView = new LoginView();
			mainView.setContentPane(loginView);
			mainView.setVisible(true);
			
			System.out.println("✅ Proyecto listo y funcionando.");
		}
		
		
	}

}
