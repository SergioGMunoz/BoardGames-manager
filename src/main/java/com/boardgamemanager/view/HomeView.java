package com.boardgamemanager.view;

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
		lbWelcome.setBounds(385, 166, 300, 30);
		add(lbWelcome);

		JLabel lbQuestion = new JLabel("¿Qué quieres hacer hoy?");
		lbQuestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbQuestion.setBounds(405, 206, 250, 25);
		add(lbQuestion);

		JButton btnUserProfile = new JButton("Perfil Usuario");
		btnUserProfile.setBounds(405, 256, 200, 30);
		add(btnUserProfile);

		JButton btnReserveGame = new JButton("Reservar juego");
		btnReserveGame.setBounds(405, 296, 200, 30);
		add(btnReserveGame);

		JButton btnViewReservations = new JButton("Ver mis reservas");
		btnViewReservations.setBounds(405, 336, 200, 30);
		add(btnViewReservations);

		JButton btnGameList = new JButton("Listado de juegos");
		btnGameList.setBounds(405, 376, 200, 30);
		add(btnGameList);

		JButton btnLogout = new JButton("Cerrar sesión");
		btnLogout.setBounds(445, 446, 120, 25);
		add(btnLogout);
	}
}
