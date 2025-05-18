package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AuthController;

public class LoginView extends JPanel implements ErrorDisplayable {

	private static final long serialVersionUID = 1L;
	private JTextField tfMail;
	private JPasswordField pfPassword;
	private JButton btnSignUp;
	private JButton btnLogin;
	private JLabel lbError;
	private AuthController authController;
	
	public JButton getBtnLogin() {
		return btnLogin;
	}
	
	@Override
	public void showError(String msg) {
		lbError.setText(msg);
		resetFields();
	}
	
	@Override
	public void clearError() {
		lbError.setText("");
	}
	
	public String getMail() {
		return tfMail.getText();
	}
	
	public String getPassword() {
	    return new String(pfPassword.getPassword());
	}
	
	private void updateBtnLogin() {
	    String mail = tfMail.getText().trim();
	    String pwd = new String(pfPassword.getPassword()).trim();

	    // Habilita el botón si ambos campos tienen texto
	    boolean isValid = !mail.isEmpty() && !pwd.isEmpty();
	    
	    if (isValid) clearError();
	    btnLogin.setEnabled(isValid);
	}
	
	public void resetFields() {
		tfMail.requestFocusInWindow();
		tfMail.setText("");
		pfPassword.setText("");
	}

	
	/**
	 * Create the panel.
	 */
	public LoginView(AuthController authController) {
		this.authController = authController;
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
		
		btnLogin = new JButton("Entrar");
		btnLogin.setEnabled(false);
		btnLogin.setBounds(189, 289, 114, 23);
		add(btnLogin);
		
		btnSignUp = new JButton("Registrarse");
		btnSignUp.setBounds(313, 289, 114, 23);
		add(btnSignUp);
		
		pfPassword = new JPasswordField();
		pfPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		pfPassword.setBounds(293, 235, 120, 20);
		add(pfPassword);
		
		lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setForeground(new Color(255, 0, 0));
		lbError.setBounds(0, 264, 640, 14);
		add(lbError);
		
		tfMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnLogin();
			}
		});
		
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnLogin();
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authController.tryLogin();
			}
		});
		
		tfMail.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER && btnLogin.isEnabled()) {
		            btnLogin.doClick(); // Simula el click
		        }
		    }
		});

		pfPassword.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER && btnLogin.isEnabled()) {
		            btnLogin.doClick();
		        }
		    }
		});

		
	}

}
