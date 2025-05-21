package controller;

import model.Reservation;
import model.ReservationDAO;
import utils.Debugger;
import utils.Session;
import utils.Validator;
import utils.exceptions.EmptyReservationFieldException;
import utils.exceptions.InvalidDateTimeFormatException;
import utils.exceptions.NotFutureDateException;
import utils.exceptions.ShopNotOpenException;
import view.ReservationDataView;

public class ReservationController extends Controller {
	private ReservationDataView reservationDataView;
	private ReservationDAO reservationDAO;
	private GameController gameController;
	
	public ReservationController() {
		this.reservationDataView = new ReservationDataView(this);
		this.reservationDAO = new ReservationDAO();
	}

	public void startReservation() {
		reservationDataView.clearMsg();
		setView(reservationDataView);
	}
	
	// Vuelve a la ventana de home
	public void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}

	// Intenta pasar al siguiente paso
	public void tryNext() {
		// Guardamos los datos en variables de reserva
		Reservation.setDateTime(reservationDataView.getDateTime());
		Reservation.setNumPlayers(reservationDataView.getPlayers());
		
		try {
			Validator.validateDateTime(reservationDataView.getDateTime());
			System.out.println("Fecha ->" + reservationDataView.getDateTime() + " VALIDA");
		} catch (InvalidDateTimeFormatException | NotFutureDateException | ShopNotOpenException
				| EmptyReservationFieldException e) {
			reservationDataView.showError(e.getMessage());
			return;
		}
		
		
		if (reservationDAO.getUserReservationBusy(Session.getId(), Reservation.getReservationDate(),
				Reservation.getTimeStart())) {
			reservationDataView.showError("El usuario ya tiene una reserva en esa franja");
			return;
		}

		//Cambiar metodo de lista de juegos para que la hora de terminar sea la duación del propio juego y no la aportemos
		Debugger.print("Horario admitido, pasando a parte 2");
		
		goNext();
	}
	
	public void goNext() {
		Debugger.print("Reserva paso 2");
		this.gameController = new GameController(true);
		gameController.startGameList();
	}

}
