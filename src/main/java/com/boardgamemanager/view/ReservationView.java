package com.boardgamemanager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class ReservationView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReservationView() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		tabbedPane.setBounds(0, 0, 1000, 700);
		add(tabbedPane);

		JPanel panelStep1 = new JPanel();
		panelStep1.setLayout(null);
		panelStep1.setBackground(new Color(204, 255, 235));
		tabbedPane.addTab("Horario y Jugadores", null, panelStep1, null);

		JLabel lbTitle = new JLabel("Reservar un juego");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(400, 50, 300, 30);
		panelStep1.add(lbTitle);

		JLabel lbPlayers = new JLabel("Número de jugadores:");
		lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPlayers.setBounds(320, 120, 150, 20);
		panelStep1.add(lbPlayers);

		JSpinner spPlayers = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spPlayers.setBounds(480, 120, 50, 20);
		panelStep1.add(spPlayers);

		JLabel lbDate = new JLabel("Fecha:");
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDate.setBounds(320, 160, 60, 20);
		panelStep1.add(lbDate);

		String[] days = new String[31];
		for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);

		String[] months = {
		    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
		    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
		};

		String[] years = { "2025", "2026" };

		JComboBox<String> cbDay = new JComboBox<>(days);
		cbDay.setBounds(380, 160, 60, 20);
		panelStep1.add(cbDay);

		JComboBox<String> cbMonth = new JComboBox<>(months);
		cbMonth.setBounds(450, 160, 100, 20);
		panelStep1.add(cbMonth);

		JComboBox<String> cbYear = new JComboBox<>(years);
		cbYear.setModel(new DefaultComboBoxModel(new String[] {"2025", "2026", "2027"}));
		cbYear.setBounds(560, 160, 70, 20);
		panelStep1.add(cbYear);

		
		JLabel lbTime = new JLabel("Hora de inicio:");
		lbTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTime.setBounds(320, 200, 150, 20);
		panelStep1.add(lbTime);

		String[] hours = new String[13];
		for (int i = 0; i < 13; i++) hours[i] = (10 + i) + ":00";

		JComboBox<String> cbHour = new JComboBox<>(hours);
		cbHour.setBounds(430, 200, 80, 20);
		panelStep1.add(cbHour);

		JLabel lbGameDuration = new JLabel("La hora de fin se calcula según la duración del juego.");
		lbGameDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbGameDuration.setBounds(320, 240, 400, 20);
		panelStep1.add(lbGameDuration);

		JLabel lbOpeningHours = new JLabel("Horario de la tienda: 10:00 – 22:00");
		lbOpeningHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbOpeningHours.setBounds(320, 260, 300, 20);
		panelStep1.add(lbOpeningHours);

		JButton btnBackHome = new JButton("Volver al inicio");
		btnBackHome.setBounds(318, 308, 132, 25);
		panelStep1.add(btnBackHome);

		JButton btnNext = new JButton("Siguiente paso");
		btnNext.setEnabled(false);
		btnNext.setBounds(498, 308, 132, 25);
		panelStep1.add(btnNext);
		
		JLabel lbError = new JLabel("Error");
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(316, 283, 314, 14);
		panelStep1.add(lbError);

		JPanel panelStep2 = new JPanel();
		panelStep2.setBackground(new Color(204, 255, 235));
		tabbedPane.addTab("Selección de Juego", null, panelStep2, null);
		
		JPanel panelStep3 = new JPanel();
		panelStep3.setBackground(new Color(204, 255, 235));
		tabbedPane.addTab("Confirmación", null, panelStep3, null);

	}
}
