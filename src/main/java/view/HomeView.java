package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class HomeView extends JPanel {

	private static final long serialVersionUID = 1L;
	private String username;

	public HomeView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbWelcome = new JLabel("Bienvenid@, " + username);
		lbWelcome.setFont(new Font("Arial", Font.BOLD, 24));
		lbWelcome.setBounds(210, 79, 300, 30);
		add(lbWelcome);

		JLabel lbQuestion = new JLabel("¿Qué quieres hacer hoy?");
		lbQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbQuestion.setBounds(230, 119, 250, 25);
		add(lbQuestion);

		JButton btnUserProfile = new JButton("Perfil Usuario");
		btnUserProfile.setBounds(230, 169, 200, 30);
		add(btnUserProfile);

		JButton btnReserveGame = new JButton("Reservar juego");
		btnReserveGame.setBounds(230, 209, 200, 30);
		add(btnReserveGame);

		JButton btnViewReservations = new JButton("Ver mis reservas");
		btnViewReservations.setBounds(230, 249, 200, 30);
		add(btnViewReservations);

		JButton btnGameList = new JButton("Listado de juegos");
		btnGameList.setBounds(230, 289, 200, 30);
		add(btnGameList);

		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(270, 329, 120, 25);
		add(btnLogout);
	}
}
