package controller;

import java.util.ArrayList;

import model.GameDAO;
import utils.Debugger;
import view.GameTable;
import view.GamesListView;

public class GameController extends Controller{
	private String [] orderValues = {"Más jugados", "Duración asc", "Duración desc", "Edad desc", "Edad asc"};
	private GamesListView gamesListView;
	private boolean reservationMode;
	private GameTable gameTable;
	private GameDAO gameDAO;
	
	public GameController(boolean reservationMode) {
		this.reservationMode = reservationMode;
		this.gameTable = new GameTable(); 
		this.gameDAO = new GameDAO();
		
		// Obtener lista de categorías 
		ArrayList<String> categories = gameDAO.getAllCategories();
		categories.add(0, "Cualquiera");
		String[] categoriesArray = categories.toArray(new String[0]);
		
		// Obtener lista de números de jugadores
	    ArrayList<String> players = gameDAO.getAllPlayerCounts();
	    players.add(0, "Cualquiera");
	    String[] playersArray = players.toArray(new String[0]);

		this.gamesListView = new GamesListView(this, gameTable, categoriesArray,playersArray, orderValues);
	}
	
	// Muestra la pantalla listado de juegos sin aplicar filtros
	public void startGameList() {
		gamesListView.clearFiltersFields();
		if(reservationMode) {
			System.out.println("Aplicando juegos con restiricciones fecha");
			gameTable.updateGames(gameDAO.getFilteredGames(null, null, null, orderValues[0], getDateFilters()));
		}else {
			gameTable.updateGames(gameDAO.getFilteredGames(null, null, null, orderValues[0], null));
		}
		setView(gamesListView);
	}
	
	// Actualiza el contenido de la tabla de juegos segun los filtros
	public void updateGameList() {
		Debugger.print("Aplicando filtros...");
		
		String name = gamesListView.getNameText();
		if (name.trim().length()<=0) name = null;
		
		String category = gamesListView.getSelectedCategory();
		if ("Cualquiera".equals(category)) category = null;

		String playersStr = gamesListView.getSelectedPlayers();
		Integer players = null;
		
		try {
		    if (!"Cualquiera".equals(playersStr)) {
		        players = Integer.parseInt(playersStr);
		    }
		} catch (Exception e) {
		    Debugger.printErr("Error al convertir jugadores: " + playersStr);
		    e.printStackTrace();
		}

		String order = gamesListView.getSelectedOrder(); 
		
		Debugger.print("Filtros aplicados:" + name + players + category + order);
		
		if(reservationMode) {
			System.out.println("Aplicando juegos con restiricciones fecha");
			gameTable.updateGames(gameDAO.getFilteredGames(name, players, category, order, getDateFilters()));
		}else {
			gameTable.updateGames(gameDAO.getFilteredGames(name, players, category, order, null));
		}
		
	}
	
	// Vuelve a la ventana de home
	public void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}
	
	// Recoger Filtros de fecha
	public String []  getDateFilters() {
		
		if (!reservationMode) {
			return null;
		}
		
		String [] dateFilters = new String [3];
		
		//Completar aqui recogiendo la info
		
		return dateFilters;
	}
	
	
}
