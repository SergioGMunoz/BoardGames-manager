package com.boardgamemanager.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamesListView extends JPanel {
    private JLabel lbTitle;
    private JTextField tfSearch;
    private JComboBox<String> cbPlayers;
    private JComboBox<String> cbType;
    private JComboBox<String> cbOrder;
    private JButton btnFilter;
    private GameTable gameTable;
    private JScrollPane scrollPane;
    private JButton btnHome;

    public GamesListView(ArrayList<Object[]> games) {
        setLayout(null);
        setPreferredSize(new Dimension(640, 480));
        setBackground(new Color(204, 255, 235));

        lbTitle = new JLabel("Nuestros Juegos", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setBounds(0, 20, 640, 30);
        add(lbTitle);

        tfSearch = new JTextField();
        tfSearch.setBounds(40, 70, 160, 22);
        add(tfSearch);

        cbPlayers = new JComboBox<>(new String[] {
            "Cualquier Nº de Jugadores"
        });
        cbPlayers.setBounds(220, 70, 130, 22);
        add(cbPlayers);

        cbType = new JComboBox<>(new String[] {
            "Cualquier Tipo"
        });
        cbType.setBounds(370, 70, 120, 22);
        add(cbType);

        cbOrder = new JComboBox<>();
        cbOrder.setModel(new DefaultComboBoxModel<>(new String[] {
            "Más jugados", "Duración asc", "Duración desc", "Edad desc", "Edad asc"
        }));
        cbOrder.setBounds(500, 70, 120, 22);
        add(cbOrder);

        btnFilter = new JButton("Aplicar filtros");
        btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnFilter.setBounds(243, 108, 150, 22);
        add(btnFilter);

        gameTable = new GameTable(games);
        scrollPane = new JScrollPane(gameTable);
        scrollPane.setBounds(40, 140, 560, 225);
        add(scrollPane);

        btnHome = new JButton("Ir a Inicio");
        btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnHome.setBounds(271, 388, 100, 22);
        add(btnHome);
    }

}
