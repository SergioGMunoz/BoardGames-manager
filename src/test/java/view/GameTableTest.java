package view;

import javax.swing.*;
import java.util.ArrayList;
import view.GameTable;

public class GameTableTest {

    public static void main(String[] args) {
        // Datos simulados como si vinieran del ResultSet
        ArrayList<Object[]> juegos = new ArrayList<>();

        juegos.add(new Object[]{
            1, // ID
            "Arnak",
            "Aventura",
            1, 4,
            "01:30:00",
            12,
            "img/arnak.png"
        });

        juegos.add(new Object[]{
            2, // ID
            "Catan",
            "Estrategia",
            3, 4,
            "01:15:00",
            10,
            "img/catan.png"
        });

        juegos.add(new Object[]{
            3, // ID
            "Caza Bombas",
            "Fiesta",
            2, 4,
            "00:20:00",
            8,
            "img/caza-bombas.png"
        });

        JFrame frame = new JFrame("Prueba de GameTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new JScrollPane(new GameTable(juegos)));
        frame.setVisible(true);
    }
}
