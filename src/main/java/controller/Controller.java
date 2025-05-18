package controller;

import javax.swing.JPanel;

import view.MainView;

public abstract class Controller {
	MainView mainView;
	
	public Controller(MainView mainView) {
        this.mainView = mainView;
    }
	
	// Actualiza el JPanel de la Vista principal 
    void setView(JPanel newView) {
    	if (!mainView.isVisible()) {
    		mainView.setVisible(true);
    	}
        mainView.setContentPane(newView);
        
        // Re dibujar la interfaz
        mainView.revalidate();
        mainView.repaint();
    }
}
