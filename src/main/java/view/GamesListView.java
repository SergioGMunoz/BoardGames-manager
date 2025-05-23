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
import controller.HomeController;
import utils.Debugger;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamesListView extends JPanel implements ErrorDisplayable{
    private JLabel lbTitle;
    private JTextField tfName;
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
    private JButton btnNext;
    private JButton btnBack;
    private JLabel lbError;
    
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
    
    public String getSelectedPlayers() {
        return (String) cbPlayers.getSelectedItem();
    }

    public String getSelectedCategory() {
        return (String) cbCategory.getSelectedItem();
    }

    public String getSelectedOrder() {
        return (String) cbOrder.getSelectedItem();
    }
    
    public String getNameText() {
        return tfName.getText();
    }
    
    public void setModeReservation(boolean on) {
    	if(on) {
    		lbTitle.setText("Seleciona un juego");
    		add(btnNext);
    		add(btnBack);
    	}else {
    		lbTitle.setText("Nuestros juegos");
    		add(btnHome);
    		add(cbPlayers);
    	}
    }
    
    @Override
	public void showError(String msg) {
		lbError.setText(msg);
	}

	@Override
	public void clearMsg() {
		lbError.setText("");
	}
	
	public void updateBtnNext () {
		Debugger.print("Actualizando btnNext");
		btnNext.setEnabled(gameTable.getSelectedRow() != -1);
		clearMsg();
	}
	
    private void init() {
        setLayout(null);
        setPreferredSize(new Dimension(640, 480));
        setBackground(new Color(204, 255, 235));

        lbTitle = new JLabel("Nuestros Juegos", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setBounds(0, 20, 640, 30);
        add(lbTitle);

        tfName = new JTextField();
        tfName.setBounds(40, 70, 160, 22);
        add(tfName);

        cbPlayers = new JComboBox<>();
        cbPlayers.setModel(new DefaultComboBoxModel<>(players));
        cbPlayers.setBounds(220, 70, 130, 22);

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
        
        lbError = new JLabel("", SwingConstants.CENTER);
        lbError.setForeground(Color.RED);
        lbError.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbError.setBounds(0, 369, 640, 14);
        add(lbError);

        btnHome = new JButton("Ir a Inicio");
        btnHome.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnHome.setBounds(271, 388, 100, 22);
        
        btnBack = new JButton("Volver");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnBack.setBounds(147, 389, 100, 22);
        
        btnNext = new JButton("Continuar");
        btnNext.setEnabled(false);
        btnNext.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNext.setBounds(397, 389, 100, 22);
        
        // Boton aplicar filtros actualiza lista
        btnFilter.addActionListener(e -> gameController.updateGameList());
        btnHome.addActionListener(e -> HomeController.goHome());
        
        btnBack.addActionListener(e -> gameController.goReservation());
        btnNext.addActionListener(e -> gameController.tryGoNext());
        
        gameTable.getSelectionModel().addListSelectionListener(e -> {
        	updateBtnNext();
        });
        
    }

	
}
