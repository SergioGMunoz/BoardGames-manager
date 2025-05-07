package com.boardgamemanager.controller;

import com.boardgamemanager.model.ConectionDB;

public class Main {

	public static void main(String[] args) {
		System.out.println("✅ Proyecto listo y funcionando.");
		ConectionDB conectionDB = new ConectionDB ("Java", "Hola12345", "localhost", "3306", "School_DB");
		//Abrir la vista
		//MainView view = new MainView(); // Crea e inicializa la ventana
	    //view.setVisible(true);
	}

}
