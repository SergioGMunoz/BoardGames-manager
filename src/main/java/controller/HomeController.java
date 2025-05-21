package controller;

import utils.Debugger;
import utils.Session;
import view.HomeView;

public class HomeController extends Controller{
	
	HomeView homeView;
	
	public HomeController() {
		super();
		this.homeView = new HomeView(this);
	}
	
	// Vuelve a la ventana de home
	public static void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}
	
	// Muestra la vista de home
	public void startHome() {
		homeView.clearMsg();
		setView(homeView);
	}
	
	// Ir a la ventana UserProfile
	public void goUserProfile() {
		UserController userController = new UserController();
		userController.startUserProfile();
	}
	
	// Ir a la ventana ReserveGame
	public void goReserveGame() {
		ReservationController reservationController = new ReservationController();
		reservationController.startReservation();
	    Debugger.print("Reserve game");
	}

	// Ir a la ventana ViewReservations
	public void goViewReservations() {
	    Debugger.print("View reservations");
	}

	// Ir a la ventana GameList
	public void goGameList() {
	    GameController gameController = new GameController(false);
	    gameController.startGameList();
	    
	}

	// Cerrar sesión y volver al login
	public void goLogin() {
		if (Session.endSession()) {
			AuthController authController = new AuthController();
			authController.startLogin();
		}else {
			homeView.showError("Error inseperado al cerrar sesión");
		}
	}

}
