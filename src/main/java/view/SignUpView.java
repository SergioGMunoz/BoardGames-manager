package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AuthController;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SignUpView extends JPanel implements ErrorDisplayable{

	private static final long serialVersionUID = 1L;
	private JPasswordField pfPassword;
	private JPasswordField pfRepeatPassword;
	private JTextField tfMail;
	private JTextField tfName;
	private JButton btnSignUp;
	private JButton btnGoBack;
	private JLabel lbError;
	private AuthController authController;

	// Rellena los campos mail, password y repeat password
	public void setFields(String mail, String pwd) {
		tfMail.setText(mail);
		pfPassword.setText(pwd);
		pfRepeatPassword.setText(pwd);
	}
	
	// Vacia todos los campos de texto
	public void clearFields() {
		tfName.setText("");
		tfMail.setText("");
		pfPassword.setText("");
		pfRepeatPassword.setText("");
	}
	
	// Devuelve el texto del campo de texto name
	public String getName() {
		return tfName.getText();
	}

	// Devuelve el texto del campo de texto mail
	public String getMail() {
		return tfMail.getText();
	}

	// Devuelve el texto del campo de texto password
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}
	
	// Devuelve el texto del campo de texto RepeatPassword
	public String getRepeatPassword() {
		return new String(pfRepeatPassword.getPassword());
	}

	// Muestra mensaje de error
	@Override
	public void showError(String msg) {
		lbError.setText(msg);
	}

	//Deja de mostrar mensaje de error
	@Override
	public void clearMsg() {
		lbError.setText("");
	}
	
	// Actualiza el btn SignUp si todos los campos estan rellenos
	public void updateBtnSignUp() {
		String name = tfName.getText().trim();
		String mail = tfMail.getText().trim();
		String pwd = new String(pfPassword.getPassword()).trim();
		String rPwd = new String(pfPassword.getPassword()).trim();

		// Habilita el botón los campos NO estan vacios
		boolean isValid = !name.isEmpty() && !mail.isEmpty() && !pwd.isEmpty() && !rPwd.isEmpty();
		    
		if (isValid) clearMsg();
		btnSignUp.setEnabled(isValid);
	}
	
	public SignUpView(AuthController authController) {
		this.authController = authController;
		
		setBackground(new Color(204, 255, 235));
		setLayout(null);

		JLabel lbTitle = new JLabel("Registro", SwingConstants.CENTER);
		lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lbTitle.setBounds(10, 70, 640, 43);
		add(lbTitle);

		JLabel lbName = new JLabel("Nombre");
		lbName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbName.setBounds(192, 137, 83, 14);
		add(lbName);

		tfName = new JTextField();
		tfName.setBounds(329, 135, 120, 20);
		add(tfName);
		tfName.setColumns(10);

		JLabel lbPassword = new JLabel("Contraseña");
		lbPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbPassword.setBounds(192, 195, 89, 14);
		add(lbPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(329, 193, 120, 20);
		add(pfPassword);

		JLabel lbRepeatPassword = new JLabel("Repite la contraseña");
		lbRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbRepeatPassword.setBounds(192, 232, 130, 14);
		add(lbRepeatPassword);

		pfRepeatPassword = new JPasswordField();
		pfRepeatPassword.setBounds(329, 229, 120, 20);
		add(pfRepeatPassword);

		lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(10, 261, 640, 14);
		add(lbError);

		btnGoBack = new JButton("Volver");
		btnGoBack.setBounds(192, 285, 113, 23);
		add(btnGoBack);

		btnSignUp = new JButton("Registrarse");
		btnSignUp.setEnabled(false);
		btnSignUp.setBounds(340, 285, 109, 23);
		add(btnSignUp);
		
		JLabel lbMail = new JLabel("Mail");
		lbMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMail.setBounds(192, 166, 83, 14);
		add(lbMail);
		
		tfMail = new JTextField();
		tfMail.setColumns(10);
		tfMail.setBounds(329, 164, 120, 20);
		add(tfMail);
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authController.trySignUp();
				}
		});
		
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnSignUp();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnGoBack.isEnabled()) {
					btnSignUp.doClick();
				}
			}
		});
		
		tfMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnSignUp();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnGoBack.isEnabled()) {
					btnSignUp.doClick();
				}
			}
		});
		
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnSignUp();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnGoBack.isEnabled()) {
					btnSignUp.doClick();
				}
			}
		});
		
		pfRepeatPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnSignUp();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnGoBack.isEnabled()) {
					btnSignUp.doClick();
				}
			}
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authController.startLogin();
			}
		});
	}

	
}
