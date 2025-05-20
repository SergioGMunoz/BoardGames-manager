package controller;

import model.UserDAO;
import utils.Debugger;
import utils.Session;
import utils.Validator;
import utils.exceptions.EmptyFieldException;
import utils.exceptions.FieldMinMaxCharactersException;
import utils.exceptions.SameFieldException;
import view.ChangePwdView;
import view.UserProfileView;

public class UserController extends Controller{
	UserProfileView userProfileView;
	UserDAO userDAO;
	ChangePwdView changePwdView;
	
	public UserController() {
		super();
		userProfileView = new UserProfileView(this);
		this.userDAO = new UserDAO();
	}

	// Muestra la ventana de perfil del usuario
	public void startUserProfile() {
		userProfileView.updateUserTextData(userDAO.getUserDataByID(Session.getId()));
		userProfileView.clearMsg();
		setView(userProfileView);
	}
	
	// Intenta validar y actualizar el nomobre y lo devuelve 
	public String tryChangeName(String newName) {
		try {
			Validator.validateName(newName);
			Debugger.print("VALIDADO CORRECTO");
			userDAO.updateUserNameByID(Session.getId(), newName);
			Session.setName(newName);
			userProfileView.showSucess("Nombre actualizado con exito");
			return newName;
		} catch (EmptyFieldException | FieldMinMaxCharactersException | SameFieldException e) {
			userProfileView.showError("El nombre " + e.getMessage());
			return Session.getName();
		}
	}
	
	// Vuelve a la venana de Home
	public void goHome() {
		HomeController homeController = new HomeController();
		homeController.startHome();
	}
	
	// Vuelve a la ventana de perfil usuario
	public void goUserProfile() {
		 startUserProfile();
	}
	
	// Muestra la vista cambiar contraseña
	public void startChangePwd() {
		changePwdView = new ChangePwdView(this);
		setView(changePwdView);
	}

	// Intenta cambiar contraseña validando
	public void tryChangePassword() {
		Debugger.print("Intentando actualizar contraseña");
	}

	// Elimina el usuario de la BBDD, termina la sesion y vuelve a login
	public void deleteAccount() {
		if (userDAO.deleteUserByID(Session.getId())) {
			Debugger.print("Borrando cuenta user");
			Session.endSession();
			AuthController authController = new AuthController();
			authController.startLogin();
		}else {
			userProfileView.showError("Error al eliminar user");
		}
	}

}
