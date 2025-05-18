package com.boardgamemanager.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameTable extends JTable {
    private DefaultTableModel tableModel;

    public GameTable(ArrayList<Object[]> games) {
        // Definimos el modelo de tabla
        String[] columnNames = {
            "ID","Nombre", "Tipo", "Nº Jugadores", "Duración", "Edad Mín", "Imagen"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        setModel(tableModel);
        setRowHeight(60);
        setFillsViewportHeight(true);
        getTableHeader().setReorderingAllowed(false);

        // Render personalizado para imágenes en columna 0
        getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText("");
                setIcon(value instanceof ImageIcon ? (ImageIcon) value : null);
            }
        });

        // Cargar datos
        updateGames(games);
    }

    // Método para actualizar los datos de la tabla
    public void updateGames(ArrayList<Object[]> games) {
        tableModel.setRowCount(0); // Limpiar

        for (Object[] game : games) {
            tableModel.addRow(new Object[] {
                game[0], // ID
                game[1], // Nombre
                game[2], // Tipo
                game[3] + "–" + game[4], // Nº Jugadores
                game[5], // Duración
                game[6], // Edad mínima
                loadImageIcon((String) game[7]) // Imagen convertida a ImageIcon
            });
        }
    }

    private ImageIcon loadImageIcon(String path) {
        int width = 50, height = 50;
        if (path == null) return null;
        File file = new File(path);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}
