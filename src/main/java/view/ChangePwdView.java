package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import controller.UserController;

import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePwdView extends JPanel implements ErrorDisplayable{

	private static final long serialVersionUID = 1L;

	private JPasswordField pfCurrentPassword;
	private JPasswordField pfNewPassword;
	private JPasswordField pfRepeatPassword;
	private JLabel lbError, lbSuccess;
	private UserController userController;
	private JButton btnChangePwd;
	private JButton btnHome;
	
	public ChangePwdView(UserController userController) {
		super();
		this.userController = userController;
		init();
	}
	
	
	// Devuelve la contraseña actual como texto (String)
	public String getCurrentPassword() {
	    return new String(pfCurrentPassword.getPassword());
	}

	// Establece el campo de contraseña actual
	public void setPfCurrentPassword(JPasswordField pfCurrentPassword) {
	    this.pfCurrentPassword = pfCurrentPassword;
	}

	// Devuelve la nueva contraseña como texto (String)
	public String getNewPassword() {
	    return new String(pfNewPassword.getPassword());
	}


	public void setPfNewPassword(JPasswordField pfNewPassword) {
		this.pfNewPassword = pfNewPassword;
	}



	public JPasswordField getPfRepeatPassword() {
		return pfRepeatPassword;
	}



	public void setPfRepeatPassword(JPasswordField pfRepeatPassword) {
		this.pfRepeatPassword = pfRepeatPassword;
	}



	@Override
	public void showError(String msg) {
		clearMsg();
		lbError.setText(msg);
	}
	
	public void showSucess(String msg) {
		clearMsg();
		lbSuccess.setText(msg);
	}

	@Override
	public void clearMsg() {
		lbError.setText("");
		lbSuccess.setText("");
	}

	public void updateBtnChangePwd(){
		boolean valid = true;
		if ( (new String(pfCurrentPassword.getPassword()).trim().length()<=0)
				|| (new String(pfNewPassword.getPassword()).trim().length()<=0)
				|| (new String(pfRepeatPassword.getPassword()).trim().length()<=0)) {
			valid = false;
		}
		btnChangePwd.setEnabled(valid);
		clearMsg();
	}
	
	public void init() {
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

		btnChangePwd = new JButton("Cambiar contraseña");
		btnChangePwd.setEnabled(false);
		btnChangePwd.setBounds(225, 290, 180, 25);
		add(btnChangePwd);

		btnHome = new JButton("Volver al inicio");
		btnHome.setBounds(225, 325, 180, 25);
		add(btnHome);
		
		lbError = new JLabel("", SwingConstants.CENTER);
		lbError.setForeground(Color.RED);
		lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbError.setBounds(0, 260, 645, 14);
		add(lbError);
		
		lbSuccess = new JLabel("", SwingConstants.CENTER);
		lbSuccess.setForeground(Color.GREEN);
		lbSuccess.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSuccess.setBounds(0, 260, 645, 14);
		add(lbSuccess);
		
		pfCurrentPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnChangePwd();
			}
		});
		
		pfNewPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnChangePwd();
			}
		});
		
		pfRepeatPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtnChangePwd();
			}
		});
		
		btnHome.addActionListener(e -> userController.goUserProfile());
		btnHome.addActionListener(e -> userController.tryChangePassword());
		
	}

}
