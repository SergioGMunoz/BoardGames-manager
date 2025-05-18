package view;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import utils.ImageUtils;

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

        // Imágenes 
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
                ImageUtils.loadImageIcon(game[7].toString(), 50, 50) // Imagen
            });
        }
    }


   
}
