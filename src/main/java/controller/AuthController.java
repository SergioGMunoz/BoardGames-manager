package controller;

import model.AuthDAO;
import utils.PasswordUtils;
import utils.Validator;
import utils.exceptions.EmptyFieldException;
import utils.exceptions.FieldTooLongException;
import view.LoginView;
import view.MainView;

public class AuthController extends Controller{
	LoginView loginView;
	AuthDAO authDAO;

	public AuthController(MainView mainView) {
		super(mainView);
		this.loginView = new LoginView(this);
		this.authDAO = new AuthDAO();
	}
	
	public void start() {
        setView(loginView);
    }
	
	// Intenta valida si los datos son correctos
	public void tryLogin() {
		String mail = loginView.getMail();
		String pwd = loginView.getPassword();
		    
		// Validar mail
	    try {
			Validator.fieldValidator(mail);
		} catch (EmptyFieldException | FieldTooLongException e) {
			loginView.showError("El mail" + e.getMessage());
			return;
		}
		    
		// Validar pwd
	    try {
			Validator.fieldValidator(pwd);
		} catch (EmptyFieldException | FieldTooLongException e) {
			loginView.showError("La contraseña" + e.getMessage());
			return;
		}
	   
		//Encriptar pwd
	    String encryptedPwd = PasswordUtils.hashPassword(pwd);

	    // Validar datos BBDD 
	    if(authDAO.userExists(mail, encryptedPwd)) {
	    	login();
	    }else {
	    	loginView.showError("Usuario o contraseña incorrecto");
	    }
	}
	
	// El login es correcto, pasar a ventana home
	private void login() {
		System.out.println("Login correcto");
	}

	
}
