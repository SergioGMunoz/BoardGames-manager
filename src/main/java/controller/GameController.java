package controller;

import java.util.ArrayList;

import model.GameDAO;
import utils.Debugger;
import view.GameTable;
import view.GamesListView;

public class GameController extends Controller{
	private String [] orderValues = {"Más jugados", "Duración asc", "Duración desc", "Edad desc", "Edad asc"};
	private GamesListView gamesListView;
	private GameTable gameTable;
	private GameDAO gameDAO;
	
	public GameController() {
		// Añadir filtros por defecto
		this.gameTable = new GameTable(); 
		
		// Obtener lista de categorías 
		ArrayList<String> categories = gameDAO.getAllCategories();
		categories.add(0, "Cualquiera");
		String[] categoriesArray = categories.toArray(new String[0]);
		
		 // Obtener lista de números de jugadores
	    ArrayList<String> players = gameDAO.getAllPlayerCounts();
	    players.add(0, "Cualquiera");
	    String[] playersArray = players.toArray(new String[0]);

		this.gamesListView = new GamesListView(this, null, categoriesArray,playersArray, orderValues);

		this.gameDAO = new GameDAO();
	}
	
	// Muestra la pantalla listado de juegos sin aplicar filtros
	public void startGameList() {
		gamesListView.clearFiltersFields();
		gameTable.updateGames(gameDAO.getFilteredGames(null, null, null, orderValues[0]));
		setView(gamesListView);
	}
	
	public void updateGameList() {
		Debugger.print("Aplicando filtros...");
		//Seguir por aqui con validaciones y mierdas
	}
}
