package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.GameController;
import utils.Debugger;

public class GamesListView extends JPanel {
    private JLabel lbTitle;
    private JTextField tfSearch;
    private JComboBox<String> cbPlayers;
    private JComboBox<String> cbCategory;
    private JComboBox<String> cbOrder;
    private JButton btnFilter;
    private GameTable gameTable;
    private JScrollPane scrollPane;
    private JButton btnHome;
    private GameController gameController; 
    private String [] categories;
    private String [] players;
    private String [] order;
    
    public GamesListView(GameController gameController, GameTable gameTable, String [] categories, String [] players, String [] order) {
    	this.gameController = gameController;
    	this.gameTable = gameTable;
    	this.categories = categories;
    	this.players = players;
    	this.order = order;
    	init();
    } 
    
    public void setGameTable (GameTable gameTable) {
    	this.gameTable = gameTable;
    }
    
    public void clearFiltersFields() {
    	Debugger.print("Limpiando campos filtros");
    }

    private void init() {
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

        cbPlayers = new JComboBox<>();
        cbPlayers.setModel(new DefaultComboBoxModel<>(players));
        cbPlayers.setBounds(220, 70, 130, 22);
        add(cbPlayers);

        cbCategory = new JComboBox<>();
        cbCategory.setModel(new DefaultComboBoxModel<>(categories));
        cbCategory.setBounds(370, 70, 120, 22);
        add(cbCategory);

        cbOrder = new JComboBox<>();
        cbOrder.setModel(new DefaultComboBoxModel<>(order));
        cbOrder.setBounds(500, 70, 120, 22);
        add(cbOrder);

        btnFilter = new JButton("Aplicar filtros");
        btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnFilter.setBounds(243, 108, 150, 22);
        add(btnFilter);

        scrollPane = new JScrollPane(gameTable);
        scrollPane.setBounds(40, 140, 560, 225);
        add(scrollPane);

        btnHome = new JButton("Ir a Inicio");
        btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnHome.setBounds(271, 388, 100, 22);
        add(btnHome);
    }

}
