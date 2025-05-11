package com.boardgamemanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GameTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	
	public void updateGames(ArrayList<Object[]> games) {
		tableModel.setRowCount(0);

		for (Object[] game : games) {
			int id = (int) game[0];
			String name = (String) game[1];
			String type = (String) game[2];
			int minPlayers = (int) game[3];
			int maxPlayers = (int) game[4];
			int duration = (int) game[5];
			int minAge = (int) game[6];
			String imagePath = (String) game[7];

			ImageIcon icon = loadImageIcon(imagePath);
			String playersText = minPlayers + "–" + maxPlayers;

			tableModel.addRow(new Object[] {
				icon, name, type, playersText, duration, minAge
			});
		}
	}
	

	private ImageIcon loadImageIcon(String path) {
		int width = 80;
		int height = 80;

		// Validar imagen
		if (path == null) {
			return null;
		}
		
		File file = new File(path);
		
		// SI existe el archivo lo escala y lo devuelve si no null
		if (file.exists()) {
			ImageIcon icon = new ImageIcon(path);
			Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} 
		return null;
	}


	public GameTablePanel() {
		setLayout(new BorderLayout());
		setBackground(new Color(204, 255, 235));

		tableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"Imagen", "Nombre", "Tipo", "Nº Jugadores", "Duración (min)", "Edad Mínima"
				}
		);

		table = new JTable(tableModel);
		table.setRowHeight(100);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);

		// Usamos la clase DefaultTableCellRenderer para cambiar el metodo de como se dibuja una celda 
		DefaultTableCellRenderer imageRenderer = new DefaultTableCellRenderer() {
			@Override
			public void setValue(Object value) {
				setText("");
				setIcon(null); 
				if (value instanceof ImageIcon) { //Si el campo es de tipo ImageIcon pinta la imagen
					setIcon((ImageIcon) value);                  
				}
			}
		};
		
		// Cambiamos la primera columa para que si encuentra una imagen la dibuje
		table.getColumnModel().getColumn(0).setCellRenderer(imageRenderer);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Listado de Juegos (con imágenes)");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 500);
			frame.setLocationRelativeTo(null);
			frame.setLayout(null);

			GameTablePanel gamePanel = new GameTablePanel();
			gamePanel.setBounds(20, 20, 1140, 400);
			frame.add(gamePanel);

			ArrayList<Object[]> juegos = new ArrayList<>();
			juegos.add(new Object[] {
				1, "Catan", "Estrategia", 3, 4, 60, 10, "img/games/catan.png"
			});
			juegos.add(new Object[] {
				2, "UNO", "Cartas", 2, 10, 20, 7, "img/games/uno.png"
			});
			juegos.add(new Object[] {
					2, "UNO", "Cartas", 2, 10, 20, 7, null
				});

			gamePanel.updateGames(juegos);

			frame.setVisible(true);
		});
	}
}
