package view;

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
		lbTitle.setBounds(202, 81, 300, 30);
		add(lbTitle);

		JLabel lbPlayers = new JLabel("Número de jugadores:");
		lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbPlayers.setBounds(162, 151, 150, 20);
		add(lbPlayers);

		spPlayers = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		spPlayers.setBounds(322, 151, 50, 20);
		add(spPlayers);

		JLabel lbDate = new JLabel("Fecha ");
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDate.setBounds(162, 191, 160, 20);
		add(lbDate);

		String[] years = { "2025", "2026", "2027" };
		cbYear = new JComboBox<>(years);
		cbYear.setBounds(242, 191, 60, 20);
		add(cbYear);

		String[] months = {
			"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
			"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
		};
		cbMonth = new JComboBox<>(months);
		cbMonth.setBounds(312, 191, 100, 20);
		add(cbMonth);

		spDay = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
		spDay.setBounds(422, 191, 50, 20);
		add(spDay);

		JLabel lbTime = new JLabel("Hora de inicio:");
		lbTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTime.setBounds(162, 231, 104, 20);
		add(lbTime);

		spHour = new JSpinner(new SpinnerNumberModel(10, 10, 21, 1));
		spHour.setBounds(272, 231, 50, 20);
		add(spHour);

		JLabel lbGameDuration = new JLabel("La hora de fin se calcula según la duración del juego.");
		lbGameDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbGameDuration.setBounds(162, 271, 400, 20);
		add(lbGameDuration);

		JLabel lbOpeningHours = new JLabel("Horario de la tienda: 10:00 – 22:00");
		lbOpeningHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbOpeningHours.setBounds(162, 291, 300, 20);
		add(lbOpeningHours);

		btnBackHome = new JButton("Volver al inicio");
		btnBackHome.setBounds(160, 339, 132, 25);
		add(btnBackHome);

		btnNext = new JButton("Siguiente paso");
		btnNext.setEnabled(false);
		btnNext.setBounds(340, 339, 132, 25);
		add(btnNext);

		lbError = new JLabel(" ",SwingConstants.CENTER);
		lbError.setForeground(Color.RED);
		lbError.setBounds(158, 314, 314, 14);
		add(lbError);
	}
}
