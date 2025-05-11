package com.boardgamemanager.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class UserProfileView extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserProfileView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Información de Usuario");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(398, 197, 300, 30);
		add(lbTitle);

		JLabel lbUsername = new JLabel("Nombre de Usuario:");
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbUsername.setBounds(348, 247, 130, 20);
		add(lbUsername);

		JTextField tfUsername = new JTextField();
		tfUsername.setBounds(488, 247, 150, 20);
		tfUsername.setEditable(false);
		add(tfUsername);

		JLabel lbGamesPlayed = new JLabel("Partidas jugadas:");
		lbGamesPlayed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGamesPlayed.setBounds(348, 287, 130, 20);
		add(lbGamesPlayed);

		JTextField tfGamesPlayed = new JTextField();
		tfGamesPlayed.setBounds(488, 287, 150, 20);
		tfGamesPlayed.setEditable(false);
		add(tfGamesPlayed);

		JLabel lbRegistrationDate = new JLabel("Fecha de registro:");
		lbRegistrationDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRegistrationDate.setBounds(348, 327, 130, 20);
		add(lbRegistrationDate);

		JTextField tfRegistrationDate = new JTextField();
		tfRegistrationDate.setBounds(488, 327, 150, 20);
		tfRegistrationDate.setEditable(false);
		add(tfRegistrationDate);

		JButton btnChangePassword = new JButton("Cambiar contraseña");
		btnChangePassword.setBounds(398, 367, 180, 25);
		add(btnChangePassword);

		JButton btnReturn = new JButton("Volver");
		btnReturn.setBounds(398, 407, 180, 25);
		add(btnReturn);
	}
}
