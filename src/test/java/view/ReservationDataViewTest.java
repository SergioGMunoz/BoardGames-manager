package view;

import controller.ReservationController;
import model.ConnectionDB;
import utils.Debugger;

public class ReservationDataViewTest {

	public static void main(String[] args) {
		// Establece si se imprimen mensajes de Debug
				Debugger.setDebug(true);
				
				// Iniciar BBDD
				if (ConnectionDB.connect("JavaDev", "Java12345", "localhost", "3306", "board_games_db")) {
					
					// Instanciar controlladores 
					ReservationController rs = new ReservationController();
					rs.startReservation();
				}
	}

}
