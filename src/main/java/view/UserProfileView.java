package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UserController;

public class UserProfileView extends JPanel implements ErrorDisplayable{
	private JTextField tfUsername, tfEmail, tfGamesPlayed, tfTopGame, tfRegistrationDate;
	private JButton btnChangePassword, btnDeleteAccount, btnHome, btnChangeUserName;
	private JCheckBox cbConfirmDelete;
	private UserController userController;
	private JLabel lbError;
	

	public UserProfileView(UserController userController) {
		super();
		this.userController = userController;
		init();
	}
	
	// Actualiza la información del usuario en los campos de texto
	public void updateUserTextData(ArrayList<Object> userData) {
	    if (userData == null || userData.size() < 5) {
	        System.err.println("❌ Datos de usuario incompletos o nulos.");
	        return;
	    }

	    tfUsername.setText(String.valueOf(userData.get(0)));
	    tfEmail.setText(String.valueOf(userData.get(1)));
	    tfGamesPlayed.setText(String.valueOf(userData.get(2)));
	    tfTopGame.setText(String.valueOf(userData.get(3)));
	    tfRegistrationDate.setText(String.valueOf(userData.get(4)));
	}
	
	@Override
	public void showError(String msg) {
		lbError.setText(msg);
	}

	@Override
	public void clearMsg() {
		lbError.setText("");
	}

	public void updateBtnChangeName() {
		btnChangeUserName.setEnabled((tfUsername.getText().trim().length()>=0));
	}
	
	public void pressBtnChangeName() {
		String newName = userController.tryChangeName(tfUsername.getText());
		tfUsername.setText(newName);
		btnChangeUserName.setEnabled(true);
	}
	
	public void init() {
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Información de Usuario", SwingConstants.CENTER);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(0, 51, 640, 30);
		add(lbTitle);

		JLabel lbUsername = new JLabel("Nombre de Usuario:");
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbUsername.setBounds(161, 104, 130, 20);
		add(lbUsername);

		tfUsername = new JTextField();
		tfUsername.setBounds(301, 104, 150, 20);
		tfUsername.setEditable(true);
		add(tfUsername);

		JLabel lbEmail = new JLabel("Correo Electrónico:");
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbEmail.setBounds(161, 134, 130, 20);
		add(lbEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(301, 134, 150, 20);
		tfEmail.setEditable(false);
		add(tfEmail);

		JLabel lbGamesPlayed = new JLabel("Partidas jugadas:");
		lbGamesPlayed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGamesPlayed.setBounds(161, 164, 130, 20);
		add(lbGamesPlayed);

		tfGamesPlayed = new JTextField();
		tfGamesPlayed.setBounds(301, 164, 150, 20);
		tfGamesPlayed.setEditable(false);
		add(tfGamesPlayed);

		JLabel lbTopGame = new JLabel("Juego más jugado:");
		lbTopGame.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTopGame.setBounds(161, 194, 130, 20);
		add(lbTopGame);

		tfTopGame = new JTextField();
		tfTopGame.setBounds(301, 194, 150, 20);
		tfTopGame.setEditable(false);
		add(tfTopGame);

		JLabel lbRegistrationDate = new JLabel("Fecha de registro:");
		lbRegistrationDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRegistrationDate.setBounds(161, 224, 130, 20);
		add(lbRegistrationDate);

		tfRegistrationDate = new JTextField();
		tfRegistrationDate.setBounds(301, 224, 150, 20);
		tfRegistrationDate.setEditable(false);
		add(tfRegistrationDate);

		btnChangePassword = new JButton("Cambiar contraseña");
		btnChangePassword.setBounds(211, 254, 180, 25);
		add(btnChangePassword);

		cbConfirmDelete = new JCheckBox("Estoy seguro de eliminar");
		cbConfirmDelete.setBackground(new Color(204, 255, 235));
		cbConfirmDelete.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbConfirmDelete.setBounds(335, 290, 169, 20);
		add(cbConfirmDelete);

		btnDeleteAccount = new JButton("Eliminar cuenta");
		btnDeleteAccount.setEnabled(false);
		btnDeleteAccount.setBounds(161, 288, 150, 25);
		add(btnDeleteAccount);

		btnHome = new JButton("Volver al Inicio");
		btnHome.setBounds(211, 328, 180, 25);
		add(btnHome);
		
		btnChangeUserName = new JButton("Cambiar");
		btnChangeUserName.setEnabled(false);
		btnChangeUserName.setBounds(460, 103, 91, 25);
		add(btnChangeUserName);
		
		lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setForeground(Color.RED);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setBounds(0, 79, 645, 14);
		add(lbError);
	}

}
