package controller;



import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Reservation;
import model.ReservationDAO;
import utils.Debugger;
import utils.PDFExporter;
import utils.Session;
import utils.Validator;
import utils.exceptions.EmptyReservationFieldException;
import utils.exceptions.InvalidDateTimeFormatException;
import utils.exceptions.NotFutureDateException;
import utils.exceptions.ShopNotOpenException;
import view.ReservationConfirmView;
import view.ReservationDataView;

public class ReservationController extends Controller {
	private ReservationDataView reservationDataView;
	private ReservationDAO reservationDAO;
	private GameController gameController;
	private ReservationConfirmView reservationConfirmView;
	
	public ReservationController() {
		this.reservationDataView = new ReservationDataView(this);
		this.reservationDAO = new ReservationDAO();
	}

	public void startReservation() {
		reservationDataView.clearMsg();
		Reservation.clearReservation();
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
		Reservation.setUserId(Session.getId());
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
		
		goStep2();
	}
	
	public void goStep2() {
		Debugger.print("Reserva paso 2");
		this.gameController = new GameController(true);
		gameController.startGameList();
	}

	public void startReservationConfirm() {
		this.reservationConfirmView = new ReservationConfirmView(this);
		reservationConfirmView.setFields(Reservation.getGameName(), Session.getName(), Reservation.getAllData());
		setView(reservationConfirmView);
	}
	
	// Confirmación de la reserva insertando y descargando PDF
	public void confirm() {
		Debugger.print("Reserva confirmada");
		
		Object [] data = Reservation.getAllData();
		
		//Insertar datos en BBDD
		boolean correct = reservationDAO.insertReservation(data);
		
		if (correct) {
		    Debugger.print("Insertado con éxito");

		    String fileName = Reservation.getReservationDate() + "_reserva_" + Reservation.getGameName() + ".pdf";

		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setDialogTitle("Guardar justificante de reserva");
		    fileChooser.setSelectedFile(new File(fileName));
		    
		    // Mostrar ventana de selecion de ruta
		    int userSelection = fileChooser.showSaveDialog(null);
		    
		   
		    if (userSelection == JFileChooser.APPROVE_OPTION) {
		    	// Obtener y nombre
		        File fileToSave = fileChooser.getSelectedFile();
		        // Pasarlo a absoluta
		        String filePath = fileToSave.getAbsolutePath();

		        // Si no tiene la extensión se la incluimos
		        if (!filePath.toLowerCase().endsWith(".pdf")) {
		            filePath += ".pdf";
		        }

		        boolean pdfCreated = PDFExporter.exportReservation(Reservation.getGameName(), data, filePath);

		        if (pdfCreated) {
		            Debugger.print("PDF creado correctamente: " + filePath);
		            JOptionPane.showMessageDialog(null, "PDF guardado correctamente:\n" + filePath, "Reserva confirmada", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            Debugger.printErr("❌ Error al generar el PDF");
		            JOptionPane.showMessageDialog(null, "Error al generar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
		        }

		    } else {
		        Debugger.print("User ha cancelado la descarga del PDF");
		    }

		    HomeController.goHome();
		} else {
		    Debugger.printErr("❌ Error al insertar reserva");
		    JOptionPane.showMessageDialog(null, "Error al crear la reserva", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}

	
