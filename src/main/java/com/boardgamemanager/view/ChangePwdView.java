package com.boardgamemanager.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

public class ChangePwdView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPasswordField pfCurrentPassword;
	private JPasswordField pfNewPassword;
	private JPasswordField pfRepeatPassword;

	public ChangePwdView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Cambiar Contraseña");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitle.setBounds(397, 193, 300, 30);
		add(lbTitle);

		JLabel lbCurrent = new JLabel("Contraseña actual:");
		lbCurrent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbCurrent.setBounds(347, 243, 130, 20);
		add(lbCurrent);

		pfCurrentPassword = new JPasswordField();
		pfCurrentPassword.setBounds(487, 243, 150, 20);
		add(pfCurrentPassword);

		JLabel lbNew = new JLabel("Nueva contraseña:");
		lbNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNew.setBounds(347, 283, 130, 20);
		add(lbNew);

		pfNewPassword = new JPasswordField();
		pfNewPassword.setBounds(487, 283, 150, 20);
		add(pfNewPassword);

		JLabel lbRepeat = new JLabel("Repetir contraseña:");
		lbRepeat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRepeat.setBounds(347, 323, 130, 20);
		add(lbRepeat);

		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(487, 323, 150, 20);
		add(pfRepeatPassword);

		JLabel lbError = new JLabel("");
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(Color.RED);
		lbError.setBounds(347, 353, 350, 20);
		add(lbError);

		JLabel lbSuccess = new JLabel("");
		lbSuccess.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSuccess.setForeground(new Color(0, 128, 0));
		lbSuccess.setBounds(347, 353, 350, 20);
		add(lbSuccess);

		JButton btnChange = new JButton("Cambiar contraseña");
		btnChange.setBounds(397, 383, 180, 25);
		add(btnChange);

		JButton btnHome = new JButton("Volver al inicio");
		btnHome.setBounds(397, 418, 180, 25);
		add(btnHome);
	}
}
