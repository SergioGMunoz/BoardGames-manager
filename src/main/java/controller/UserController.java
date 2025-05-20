package controller;

import model.UserDAO;
import utils.Debugger;
import utils.PasswordUtils;
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

	    String currentPassword = changePwdView.getCurrentPassword();
	    String newPassword = changePwdView.getNewPassword();
	    String repeatPassword = changePwdView.getPfRepeatPassword();

	    Debugger.print("Contraseña actual: " + currentPassword);
	    Debugger.print("Nueva contraseña: " + newPassword);
	    Debugger.print("Repetir contraseña: " + repeatPassword);
	    
	    // Valida que la contraseña sea correcta
	    if(!PasswordUtils.hashPassword(currentPassword).equals(Session.getPassword())) {
	    	changePwdView.showError("La contraseña actual no es correcta");
	    	return;
	    }
	    
	    Debugger.print("Contraseña actual correcta");
	    
	    // Si las contraseñas no coinciden
	    if (!newPassword.equals(repeatPassword)) {
	    	changePwdView.showError("Las contraseñas no coinciden");
	    	return;
	    }
	    
	    Debugger.print("Las contraseñas coinciden");
	    
	    // Valida que la constraseña cumpla con la validación
	    try {
			Validator.validatePassword(newPassword);
		} catch (EmptyFieldException | FieldMinMaxCharactersException | SameFieldException e) {
			changePwdView.showError("La contraseñas " + e.getMessage());
	    	return;
		}
	    
	    Debugger.print("Contraseña valida, actualizando...");
	    
	    userDAO.updatePasswordByID(Session.getId(), PasswordUtils.hashPassword(newPassword));
	    Session.setPassword(newPassword);
	    changePwdView.showSucess("Contraseña actualizada con exito");
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
