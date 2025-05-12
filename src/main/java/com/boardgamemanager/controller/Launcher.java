package com.boardgamemanager.controller;

import java.util.ArrayList;

import com.boardgamemanager.model.ConectionDB;
import com.boardgamemanager.view.GamesListView;
import com.boardgamemanager.view.LoginView;
import com.boardgamemanager.view.MainView;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("✅ Proyecto listo y funcionando.");
		ConectionDB conectionDB = new ConectionDB ("Java", "Hola12345", "localhost", "3306", "School_DB");
		
		MainView mainView = new MainView();
		LoginView lv = new LoginView();
		
		
		 ArrayList<Object[]> juegos = new ArrayList<>();
	     juegos.add(new Object[]{1, "Catan", "Estrategia", 3, 4, 60, 10, "img/games/catan.png"});
	     juegos.add(new Object[]{2, "UNO", "Cartas", 2, 10, 20, 7, "img/games/uno.png"});
	     juegos.add(new Object[]{3, "Ajedrez", "Clásico", 2, 2, 30, 8, null});

		GamesListView rsv = new GamesListView(juegos);
		
		mainView.setContentPane(rsv);
		mainView.setVisible(true);
	}

}
