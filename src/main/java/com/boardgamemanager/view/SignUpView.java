package com.boardgamemanager.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class SignUpView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfUser;
	private JPasswordField pfPassword;
	private JPasswordField pfRepeatPassword;

	/**
	 * Create the panel.
	 */
	public SignUpView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Registro");
		lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(448, 200, 205, 43);
		add(lbTitle);

		JLabel lbUser = new JLabel("Usuario");
		lbUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbUser.setBounds(399, 260, 83, 14);
		add(lbUser);

		tfUser = new JTextField();
		tfUser.setBounds(492, 257, 120, 20);
		add(tfUser);
		tfUser.setColumns(10);

		JLabel lbPassword = new JLabel("Contraseña");
		lbPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbPassword.setBounds(399, 296, 89, 14);
		add(lbPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(492, 293, 120, 20);
		add(pfPassword);

		JLabel lbRepeatPassword = new JLabel("Repite la contraseña");
		lbRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRepeatPassword.setBounds(355, 332, 130, 14);
		add(lbRepeatPassword);

		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(492, 329, 120, 20);
		add(pfRepeatPassword);

		JLabel lbError = new JLabel("");
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(399, 360, 300, 14);
		add(lbError);

		JButton btnLogin = new JButton("Volver");
		btnLogin.setEnabled(false);
		btnLogin.setBounds(399, 385, 89, 23);
		add(btnLogin);

		JButton btnSignUp = new JButton("Registrarse");
		btnSignUp.setEnabled(false);
		btnSignUp.setBounds(523, 385, 89, 23);
		add(btnSignUp);
	}

}
