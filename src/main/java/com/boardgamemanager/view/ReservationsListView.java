package com.boardgamemanager.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ReservationsListView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable reservationTable;
	private JCheckBox cbConfirmDelete;
	private JButton btnDelete;
	private JButton btnHome;

	public ReservationsListView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Listado de Reservas");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(0, 52, 640, 30);
		add(lbTitle);


		DefaultTableModel modelo = new DefaultTableModel(
			new Object[][] {},
			new String[] {"ID", "Nombre", "Juego", "Fecha", "Hora"}
		);
		reservationTable = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(reservationTable);
		scrollPane.setBounds(80, 102, 480, 203);
		add(scrollPane);

		cbConfirmDelete = new JCheckBox("Estoy seguro de eliminar la reserva seleccionada");
		cbConfirmDelete.setBounds(80, 333, 400, 25);
		cbConfirmDelete.setBackground(getBackground());
		add(cbConfirmDelete);

		btnDelete = new JButton("Eliminar");
		btnDelete.setBounds(80, 373, 120, 25);
		btnDelete.setEnabled(false); 
		add(btnDelete);

		btnHome = new JButton("Ir a inicio");
		btnHome.setBounds(440, 373, 120, 25);
		add(btnHome);

		// Listeners
		cbConfirmDelete.addActionListener(e -> {
			btnDelete.setEnabled(cbConfirmDelete.isSelected() && reservationTable.getSelectedRow() != -1);
		});

		reservationTable.getSelectionModel().addListSelectionListener(e -> {
			btnDelete.setEnabled(cbConfirmDelete.isSelected() && reservationTable.getSelectedRow() != -1);
		});
	}

}
