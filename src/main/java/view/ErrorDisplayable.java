package view;

public interface ErrorDisplayable {
	// Vista que permite lanzar errores 
	
	// Mostrar error por pantalla
    void showError(String msg);
    
    // Dejar de mostrar error y otros mensajes
    void clearMsg();
}
