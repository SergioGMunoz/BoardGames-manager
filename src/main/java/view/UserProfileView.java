package view;

import javax.swing.*;
import java.awt.*;

public class UserProfileView extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserProfileView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Información de Usuario", SwingConstants.CENTER);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(0, 54, 640, 30);
		add(lbTitle);

		JLabel lbUsername = new JLabel("Nombre de Usuario:");
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbUsername.setBounds(161, 104, 130, 20);
		add(lbUsername);

		JTextField tfUsername = new JTextField();
		tfUsername.setBounds(301, 104, 150, 20);
		tfUsername.setEditable(true);
		add(tfUsername);

		JLabel lbEmail = new JLabel("Correo Electrónico:");
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbEmail.setBounds(161, 134, 130, 20);
		add(lbEmail);

		JTextField tfEmail = new JTextField();
		tfEmail.setBounds(301, 134, 150, 20);
		tfEmail.setEditable(false);
		add(tfEmail);

		JLabel lbGamesPlayed = new JLabel("Partidas jugadas:");
		lbGamesPlayed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGamesPlayed.setBounds(161, 164, 130, 20);
		add(lbGamesPlayed);

		JTextField tfGamesPlayed = new JTextField();
		tfGamesPlayed.setBounds(301, 164, 150, 20);
		tfGamesPlayed.setEditable(false);
		add(tfGamesPlayed);

		JLabel lbTopGame = new JLabel("Juego más jugado:");
		lbTopGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTopGame.setBounds(161, 194, 130, 20);
		add(lbTopGame);

		JTextField tfTopGame = new JTextField();
		tfTopGame.setBounds(301, 194, 150, 20);
		tfTopGame.setEditable(false);
		add(tfTopGame);

		JLabel lbRegistrationDate = new JLabel("Fecha de registro:");
		lbRegistrationDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRegistrationDate.setBounds(161, 224, 130, 20);
		add(lbRegistrationDate);

		JTextField tfRegistrationDate = new JTextField();
		tfRegistrationDate.setBounds(301, 224, 150, 20);
		tfRegistrationDate.setEditable(false);
		add(tfRegistrationDate);

		JButton btnChangePassword = new JButton("Cambiar contraseña");
		btnChangePassword.setBounds(211, 254, 180, 25);
		add(btnChangePassword);

		JCheckBox cbConfirmDelete = new JCheckBox("Estoy seguro de eliminar");
		cbConfirmDelete.setBackground(new Color(204, 255, 235));
		cbConfirmDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbConfirmDelete.setBounds(335, 290, 169, 20);
		add(cbConfirmDelete);

		JButton btnDeleteAccount = new JButton("Eliminar cuenta");
		btnDeleteAccount.setEnabled(false);
		btnDeleteAccount.setBounds(161, 288, 150, 25);
		add(btnDeleteAccount);

		JButton btnHome = new JButton("Volver al Inicio");
		btnHome.setBounds(211, 328, 180, 25);
		add(btnHome);
	}
}
