package controller;

import model.Reservation;
import model.ReservationDAO;
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
		try {
			Validator.validateDateTime(reservationDataView.getDateTime());
			System.out.println("Fecha->" + reservationDataView.getDateTime() + " valida");
		} catch (InvalidDateTimeFormatException | NotFutureDateException | ShopNotOpenException
				| EmptyReservationFieldException e) {
			reservationDataView.showError(e.getMessage());
			return;
		}
		
		Reservation.setDateTime(reservationDataView.getDateTime());
		
		reservationDAO.getUserReservationBusy(Session.getId(), Reservation.getReservationDate(), Reservation.getTimeStart());

		//Cambiar metodo de lista de juegos para que la hora de terminar sea la duación del propio juego y no la aportemos
		
		goNext();
	}
	
	public void goNext() {
		
	}

}
