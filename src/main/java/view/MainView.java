package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Color;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static MainView instance;
	JPanel panel;

	public MainView() {
        instance = this; 
        init(); 
    }
	
	// Devuelve la misma instancia
	public static MainView getInstance() {
        return instance;
    }

	/**
	 * Create the frame.
	 */
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		getContentPane().add(panel);
		panel.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
