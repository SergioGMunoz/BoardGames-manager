package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.HomeController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeView extends JPanel implements ErrorDisplayable{

	private static final long serialVersionUID = 1L;
	private JLabel lbWelcome;
	private JButton btnUserProfile, btnReserveGame, btnViewReservations, btnGameList, btnLogout;
	private JLabel lbError;
	private HomeController homeController;

	public HomeView(HomeController homeController) {
		this.homeController= homeController;
		init();
	}
	
	@Override
	public void showError(String msg) {
		lbError.setText(msg);
	}

	@Override
	public void clearMsg() {
		lbError.setText("");
	}
	
	// Establece el nombre del usuario en lbWelcome
	public void setLbWelcomeText(String name) {
		lbWelcome.setText("¡Hola  " + name + "!");
	}

	public void init() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		lbWelcome = new JLabel("Hola!", SwingConstants.CENTER);
		lbWelcome.setFont(new Font("Arial", Font.BOLD, 24));
		lbWelcome.setBounds(180, 78, 300, 30);
		add(lbWelcome);

		JLabel lbQuestion = new JLabel("¿Qué quieres hacer hoy?", SwingConstants.CENTER);
		lbQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbQuestion.setBounds(202, 119, 250, 25);
		add(lbQuestion);

		btnUserProfile = new JButton("Perfil Usuario");
		btnUserProfile.setBounds(230, 169, 200, 30);
		add(btnUserProfile);

		btnReserveGame = new JButton("Reservar juego");
		btnReserveGame.setBounds(230, 209, 200, 30);
		add(btnReserveGame);

		btnViewReservations = new JButton("Ver mis reservas");
		btnViewReservations.setBounds(230, 249, 200, 30);
		add(btnViewReservations);

		btnGameList = new JButton("Listado de juegos");
		btnGameList.setBounds(230, 289, 200, 30);
		add(btnGameList);

		btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(270, 329, 120, 25);
		add(btnLogout);
		
		lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setForeground(Color.RED);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setBounds(118, 365, 439, 14);
		add(lbError);
		
		// User Profile
		btnUserProfile.addActionListener(e -> homeController.goUserProfile());
		// Reserve game
		btnReserveGame.addActionListener(e -> homeController.goReserveGame());
		// View List Reservations
		btnViewReservations.addActionListener(e -> homeController.goViewReservations());
		// Game list
		btnGameList.addActionListener(e -> homeController.goGameList());
		// Cerrar sesión
		btnLogout.addActionListener(e -> homeController.goLogin());

		
	}
}
