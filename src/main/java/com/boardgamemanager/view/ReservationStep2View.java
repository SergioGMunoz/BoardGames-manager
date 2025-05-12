package com.boardgamemanager.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReservationStep2View extends JPanel {
    private GameTable gameTable;
    private JScrollPane scrollPane;
    private JButton btnVolver;
    private JButton btnSiguiente;
    private JLabel titulo;

    public ReservationStep2View(ArrayList<Object[]> juegos) {
        setLayout(null);
        setBackground(new Color(204, 255, 235));

        titulo = new JLabel("Selecciona el juego", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 30, 640, 30);
        add(titulo);

        gameTable = new GameTable(juegos);
        scrollPane = new JScrollPane(gameTable);
        scrollPane.setBounds(40, 80, 560, 280);
        add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnVolver.setBounds(177, 394, 132, 25);
        add(btnVolver);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setEnabled(false);
        btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnSiguiente.setBounds(334, 394, 132, 25);
        add(btnSiguiente);
    }
}
