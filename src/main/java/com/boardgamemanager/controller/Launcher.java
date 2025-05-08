package com.boardgamemanager.controller;

import javax.swing.JPanel;

import com.boardgamemanager.model.ConectionDB;
import com.boardgamemanager.view.MainView;
import com.boardgamemanager.view.LoginView;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("✅ Proyecto listo y funcionando.");
		ConectionDB conectionDB = new ConectionDB ("Java", "Hola12345", "localhost", "3306", "School_DB");
		
		MainView mainView = new MainView();
		LoginView lv = new LoginView();
		mainView.setContentPane(lv);
		mainView.setVisible(true);
		
		//MainView view = new MainView(); // Crea e inicializa la ventana
	    //view.setVisible(true);
	}

}
