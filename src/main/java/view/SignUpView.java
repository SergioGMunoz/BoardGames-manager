package view;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfName;
	private JPasswordField pfPassword;
	private JPasswordField pfRepeatPassword;
	private JTextField tfMail;

	public SignUpView() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Registro", SwingConstants.CENTER);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(0, 117, 640, 43);
		add(lbTitle);

		JLabel lbName = new JLabel("Nombre");
		lbName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbName.setBounds(182, 184, 83, 14);
		add(lbName);

		tfName = new JTextField();
		tfName.setBounds(319, 182, 120, 20);
		add(tfName);
		tfName.setColumns(10);

		JLabel lbPassword = new JLabel("Contraseña");
		lbPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbPassword.setBounds(182, 242, 89, 14);
		add(lbPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(319, 240, 120, 20);
		add(pfPassword);

		JLabel lbRepeatPassword = new JLabel("Repite la contraseña");
		lbRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRepeatPassword.setBounds(182, 279, 130, 14);
		add(lbRepeatPassword);

		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(319, 276, 120, 20);
		add(pfRepeatPassword);

		JLabel lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(0, 308, 640, 14);
		add(lbError);

		JButton btnLogin = new JButton("Volver");
		btnLogin.setBounds(206, 332, 89, 23);
		add(btnLogin);

		JButton btnSignUp = new JButton("Registrarse");
		btnSignUp.setEnabled(false);
		btnSignUp.setBounds(330, 332, 89, 23);
		add(btnSignUp);
		
		JLabel lbMail = new JLabel("Mail");
		lbMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMail.setBounds(182, 213, 83, 14);
		add(lbMail);
		
		tfMail = new JTextField();
		tfMail.setColumns(10);
		tfMail.setBounds(319, 211, 120, 20);
		add(tfMail);
	}
}
