package com.boardgamemanager.view;

import javax.swing.*;
import java.awt.*;

public class ReservationStep1View extends JPanel {

	private static final long serialVersionUID = 1L;

	private JSpinner spPlayers;
	private JComboBox<String> cbYear;
	private JComboBox<String> cbMonth;
	private JSpinner spDay;
	private JSpinner spHour;
	private JButton btnBackHome;
	private JButton btnNext;
	private JLabel lbError;

	public ReservationStep1View() {
		setLayout(null);
		setBackground(new Color(204, 255, 235));

		JLabel lbTitle = new JLabel("Reservar un juego");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(400, 176, 300, 30);
		add(lbTitle);

		JLabel lbPlayers = new JLabel("Número de jugadores:");
		lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPlayers.setBounds(320, 246, 150, 20);
		add(lbPlayers);

		spPlayers = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spPlayers.setBounds(480, 246, 50, 20);
		add(spPlayers);

		JLabel lbDate = new JLabel("Fecha ");
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDate.setBounds(320, 286, 160, 20);
		add(lbDate);

		String[] years = { "2025", "2026", "2027" };
		cbYear = new JComboBox<>(years);
		cbYear.setBounds(400, 286, 60, 20);
		add(cbYear);

		String[] months = {
			"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
			"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
		};
		cbMonth = new JComboBox<>(months);
		cbMonth.setBounds(470, 286, 100, 20);
		add(cbMonth);

		spDay = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
		spDay.setBounds(580, 286, 50, 20);
		add(spDay);

		JLabel lbTime = new JLabel("Hora de inicio:");
		lbTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTime.setBounds(320, 326, 104, 20);
		add(lbTime);

		spHour = new JSpinner(new SpinnerNumberModel(10, 10, 21, 1));
		spHour.setBounds(430, 326, 50, 20);
		add(spHour);

		JLabel lbGameDuration = new JLabel("La hora de fin se calcula según la duración del juego.");
		lbGameDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbGameDuration.setBounds(320, 366, 400, 20);
		add(lbGameDuration);

		JLabel lbOpeningHours = new JLabel("Horario de la tienda: 10:00 – 22:00");
		lbOpeningHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbOpeningHours.setBounds(320, 386, 300, 20);
		add(lbOpeningHours);

		btnBackHome = new JButton("Volver al inicio");
		btnBackHome.setBounds(318, 434, 132, 25);
		add(btnBackHome);

		btnNext = new JButton("Siguiente paso");
		btnNext.setEnabled(false);
		btnNext.setBounds(498, 434, 132, 25);
		add(btnNext);

		lbError = new JLabel(" ");
		lbError.setForeground(Color.RED);
		lbError.setBounds(316, 409, 314, 14);
		add(lbError);
	}
}
