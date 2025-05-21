package controller;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.GameDAO;
import model.Reservation;
import model.ReservationDAO;
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
		gamesListView.setModeReservation(reservationMode);
	}
	
	// Muestra la pantalla listado de juegos sin aplicar filtros
	public void startGameList() {
		gamesListView.clearFiltersFields();
		if(reservationMode) {
			System.out.println("Aplicando juegos con restiricciones fecha");
			gameTable.updateGames(gameDAO.getFilteredGames(null, Reservation.getNumPlayers(), 
					null, orderValues[0], Reservation.getReservationDate(),Reservation.getTimeStart()));
		}else {
			gameTable.updateGames(gameDAO.getFilteredGames(null, null, null, orderValues[0], null, null));
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
		
		
		if(reservationMode) {
			Debugger.print("Filtros aplicados:" + name  + category + order);
			System.out.println("Aplicando juegos con restiricciones fecha");
			gameTable.updateGames(gameDAO.getFilteredGames(name, Reservation.getNumPlayers(), category, order,
					Reservation.getReservationDate(),Reservation.getTimeStart()));
		}else {
			gameTable.updateGames(gameDAO.getFilteredGames(name, players, category, order, null,null));
		}
		
	}
	
	// Vuelve a la ventana de home
	public void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}

	// Botones cuando esta en reservation mode
	public void goReservation() {
		ReservationController reservationController = new ReservationController();
		reservationController.startReservation();
	}
	
	public void tryGoNext() {
		System.out.println("Intentando ir a Reservation parte 3");
		
		int selectedRow = gameTable.getSelectedRow();

		if (selectedRow == -1) {
			 Debugger.printErr("Ninguna fila selecionada en game table");
			 return;
		} 
		
		Integer gameID = (Integer) gameTable.getValueAt(selectedRow, 0); 
		String gameName = (String) gameTable.getValueAt(selectedRow, 1); 
		Reservation.setGameId(gameID);
		Reservation.setGameName(gameName);
		Debugger.print("Juego selecionado id -> " + gameID);
		
		// Recoger los datos de tiempo
	    LocalTime startTime = LocalTime.parse(Reservation.getTimeStart()); 
	    // Convertir duración a minutos
	    LocalTime duration = LocalTime.parse(gameDAO.getGameDurationByID(gameID));      
	    Duration dur = Duration.between(LocalTime.MIN, duration);

	    // Calcular tiempo terminar
	    LocalTime endTime = startTime.plus(dur);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	    Reservation.setTimeEnd(endTime.format(formatter));

		
	    // Validar si la hora de terminar el usuario tiene una reserva de algun juego
	    ReservationDAO reservationDAO = new ReservationDAO();
	    Debugger.print("Llamando al método getUserReservationBusy con tiempo de fin.");
	    Debugger.print("DATOS → userId: " + Reservation.getUserId()
	        + ", fecha: " + Reservation.getReservationDate()
	        + ", hora inicio: " + Reservation.getTimeStart()
	        + ", hora fin: " + Reservation.getTimeEnd());

	    boolean userBusy = reservationDAO.getUserReservationBusy(Reservation.getUserId(), Reservation.getReservationDate(), 
	    		Reservation.getTimeStart(), Reservation.getTimeEnd());
		
	    if (userBusy) {
	    	gamesListView.showError("La reserva de ese juego se solapa con otra tuya");
	    }else {
	    	goNexr();
	    }
	}
	
	public void goNexr() {
		System.out.println("Moviendonos a reservation parte 3");
		ReservationController reservationController = new ReservationController();
		reservationController.startReservationConfirm();
	}
	
	
}
