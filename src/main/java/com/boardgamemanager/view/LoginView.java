package com.boardgamemanager.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public LoginView() {
		setLayout(null);
		
		JLabel lbLogin = new JLabel("Iniciar Sesión");
		lbLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbLogin.setFont(new Font("Arial", Font.BOLD, 15));
		lbLogin.setBounds(233, 31, 130, 43);
		add(lbLogin);

	}
}
