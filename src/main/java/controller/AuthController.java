package controller;

import model.AuthDAO;

import utils.PasswordUtils;
import utils.Session;
import utils.exceptions.*;
import utils.Validator;

import view.LoginView;
import view.MainView;
import view.SignUpView;

public class AuthController extends Controller{
	LoginView loginView;
	AuthDAO authDAO;
	SignUpView signUpView;

	public AuthController() {
		super();
		this.loginView = new LoginView(this);
		this.authDAO = new AuthDAO();
	}
	
	//Inicia la ventana de login
	public void startLogin() {
        setView(loginView);
        loginView.clearMsg();
        loginView.resetFields();
    }
	
	// Intenta login, valida si los datos son correctos
	public void tryLogin() {
		String mail = loginView.getMail();
		String pwd = loginView.getPassword();
	   
		//Encriptar pwd
	    String encryptedPwd = PasswordUtils.hashPassword(pwd);

	    // Validar datos BBDD 
	    if(authDAO.userExists(mail, encryptedPwd)) {
	    	Session.startSession(authDAO.getUserByMail(mail));
	    	goHome();
	    }else {
	    	loginView.showError("Usuario o contraseña incorrecto");
	    }
	}
	
	// El login es correcto, ir a ventana home
	private void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}
	
	//Inicia la ventana de signUp
	public void goSignUp() {
		if(signUpView == null) {
			signUpView = new SignUpView(this);
		}
		signUpView.clearFields();
		signUpView.setFields(loginView.getMail(), loginView.getPassword());
		setView(signUpView);
	}
	
	// Intenta dar de alta un usuario, valida si datos correctos
	public void trySignUp() {
		String name=signUpView.getName();
		String mail=signUpView.getMail();
		String pwd=signUpView.getPassword();
		String rPwd=signUpView.getRepeatPassword();
		
		// Validar name
		try {
			Validator.validateName(name);
		} catch (EmptyFieldException | FieldMinMaxCharactersException e) {
			signUpView.showError("El nombre " + e.getMessage());
			return;
		}
		
		// Validar mail
		try {
			Validator.validateMail(mail);
		} catch (EmptyFieldException | MailNotValidException | FieldMinMaxCharactersException e) {
			signUpView.showError("El mail " + e.getMessage());
			return;
		}
		
		// Validar password
		try {
			Validator.validatePassword(pwd);
		} catch (EmptyFieldException | FieldMinMaxCharactersException e) {
			signUpView.showError("La contraseña " + e.getMessage());
			return;
		}
		
		// Validar password iguales
		if (!pwd.equals(rPwd)) {
			signUpView.showError("Las contraseñas deben ser iguales");
			return;
		}
		
		// Validar mail no existe
		if (authDAO.mailExists(mail)) {
			signUpView.showError(mail + " ya esta registrado");
			return;
		}
		
		// Insertar registro BBDD
		if (!authDAO.registerUser(name, mail, PasswordUtils.hashPassword(pwd))) {
			signUpView.showError("Error inesperado");
			return;
		}
		
		goLoginFromSignUp();
	}
	
	// El usuario se registra volver a login rellenar datos
	private void goLoginFromSignUp() {
		startLogin();
		loginView.setFields(signUpView.getMail(), signUpView.getPassword());
		loginView.showSucess("Usuario registrado");
	}
	
	
}
