package view;

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
		lbTitle.setBounds(225, 100, 300, 30);
		add(lbTitle);

		JLabel lbCurrent = new JLabel("Contraseña actual:");
		lbCurrent.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbCurrent.setBounds(175, 150, 130, 20);
		add(lbCurrent);

		pfCurrentPassword = new JPasswordField();
		pfCurrentPassword.setBounds(315, 150, 150, 20);
		add(pfCurrentPassword);

		JLabel lbNew = new JLabel("Nueva contraseña:");
		lbNew.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNew.setBounds(175, 190, 130, 20);
		add(lbNew);

		pfNewPassword = new JPasswordField();
		pfNewPassword.setBounds(315, 190, 150, 20);
		add(pfNewPassword);

		JLabel lbRepeat = new JLabel("Repetir contraseña:");
		lbRepeat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRepeat.setBounds(175, 230, 130, 20);
		add(lbRepeat);

		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(315, 230, 150, 20);
		add(pfRepeatPassword);

		JLabel lbError = new JLabel("");
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(Color.RED);
		lbError.setBounds(175, 260, 350, 20);
		add(lbError);

		JLabel lbSuccess = new JLabel("");
		lbSuccess.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSuccess.setForeground(new Color(0, 128, 0));
		lbSuccess.setBounds(175, 260, 350, 20);
		add(lbSuccess);

		JButton btnChange = new JButton("Cambiar contraseña");
		btnChange.setBounds(225, 290, 180, 25);
		add(btnChange);

		JButton btnHome = new JButton("Volver al inicio");
		btnHome.setBounds(225, 325, 180, 25);
		add(btnHome);
	}
}
