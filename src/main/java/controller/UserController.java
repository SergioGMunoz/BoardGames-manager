package controller;

import model.UserDAO;
import utils.Session;
import utils.Validator;
import utils.exceptions.EmptyFieldException;
import utils.exceptions.FieldMinMaxCharactersException;
import utils.exceptions.SameFieldException;
import view.UserProfileView;

public class UserController extends Controller{
	UserProfileView userProfileView;
	UserDAO userDAO;
	
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
			userDAO.updateUserNameByID(Session.getId(), newName);
			Session.setName(newName);
			return newName;
		} catch (EmptyFieldException | FieldMinMaxCharactersException | SameFieldException e) {
			userProfileView.showError("El nombre " + e);
			return Session.getName();
		}
	}

}
