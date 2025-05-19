package controller;

import javax.swing.JPanel;

import view.MainView;

public abstract class Controller {
	static MainView mainView;
	
	public Controller() {
		mainView = MainView.getInstance();
		
		if (mainView == null) {
			this.mainView = new MainView();
		}
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
