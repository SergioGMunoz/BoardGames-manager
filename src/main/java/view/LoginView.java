package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfMail;
	private JPasswordField pfPassword;

	/**
	 * Create the panel.
	 */
	public LoginView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);
		
		JLabel lbTitle = new JLabel("Iniciar Sesión");
		lbTitle.setBackground(new Color(240, 240, 240));
		lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(222, 140, 205, 43);
		add(lbTitle);
		
		tfMail = new JTextField();
		tfMail.setBounds(293, 199, 120, 20);
		add(tfMail);
		tfMail.setColumns(10);
		
		JLabel lbMail = new JLabel("E-Mail");
		lbMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMail.setBounds(200, 202, 83, 14);
		add(lbMail);
		
		JLabel lbPassword = new JLabel("Contraseña");
		lbPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbPassword.setBounds(200, 238, 89, 14);
		add(lbPassword);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setEnabled(false);
		btnLogin.setBounds(200, 289, 103, 23);
		add(btnLogin);
		
		JButton btnSignUp = new JButton("Registrarse");
		btnSignUp.setEnabled(false);
		btnSignUp.setBounds(313, 289, 100, 23);
		add(btnSignUp);
		
		pfPassword = new JPasswordField();
		pfPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		pfPassword.setBounds(293, 235, 120, 20);
		add(pfPassword);
		
		JLabel lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(0, 264, 640, 14);
		add(lbError);

	}
}
