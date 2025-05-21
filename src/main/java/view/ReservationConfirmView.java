package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.HomeController;
import controller.ReservationController;

public class ReservationConfirmView extends JPanel {
    private JLabel lbTitle;
    private JTextField tfPlayers;
    private JTextField tfDate;
    private JTextField tfStartTime;
    private JTextField tfEndTime;
    private JTextField tfReservedName;
    private JTextField tfGame;

    private JButton btnConfirm;
    private JButton btnBack;
    private JButton btnCancel;

    private ReservationController reservationController;

    public ReservationConfirmView(ReservationController reservationController) {
        this.reservationController = reservationController;
        init();
    }
    
    // Rellena los campos de texto
    public void setFields(String gameName, String userName, Object[] data) {
        tfPlayers.setText(String.valueOf(data[2]));            
        tfDate.setText((String) data[3]);                       
        tfStartTime.setText((String) data[4]);                  
        tfEndTime.setText((String) data[5]);                    
        tfReservedName.setText(userName);              
        tfGame.setText(gameName);                               
    }


    private void init() {
        setLayout(null);
        setBackground(new Color(204, 255, 235));

        lbTitle = new JLabel("Confirma tu reserva", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setBounds(0, 50, 640, 30);
        add(lbTitle);

        JLabel lbPlayers = new JLabel("Numero de Jugadores");
        lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbPlayers.setBounds(130, 100, 150, 20);
        add(lbPlayers);

        tfPlayers = new JTextField();
        tfPlayers.setEditable(false);
        tfPlayers.setBounds(290, 100, 210, 20);
        add(tfPlayers);

        JLabel lbDate = new JLabel("Fecha");
        lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbDate.setBounds(130, 130, 150, 20);
        add(lbDate);

        tfDate = new JTextField();
        tfDate.setEditable(false);
        tfDate.setBounds(290, 130, 210, 20);
        add(tfDate);

        JLabel lbStart = new JLabel("Empieza");
        lbStart.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbStart.setBounds(130, 160, 150, 20);
        add(lbStart);

        tfStartTime = new JTextField();
        tfStartTime.setEditable(false);
        tfStartTime.setBounds(290, 160, 210, 20);
        add(tfStartTime);

        JLabel lbEnd = new JLabel("Termina");
        lbEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbEnd.setBounds(130, 190, 150, 20);
        add(lbEnd);

        tfEndTime = new JTextField();
        tfEndTime.setEditable(false);
        tfEndTime.setBounds(290, 190, 210, 20);
        add(tfEndTime);

        JLabel lbName = new JLabel("Reserva a nombre");
        lbName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbName.setBounds(130, 220, 150, 20);
        add(lbName);

        tfReservedName = new JTextField();
        tfReservedName.setEditable(false);
        tfReservedName.setBounds(290, 220, 210, 20);
        add(tfReservedName);

        JLabel lbGame = new JLabel("Juego selecionado");
        lbGame.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbGame.setBounds(130, 250, 150, 20);
        add(lbGame);

        tfGame = new JTextField();
        tfGame.setEditable(false);
        tfGame.setBounds(290, 250, 210, 20);
        add(tfGame);

        btnConfirm = new JButton("Confirmar y descargar");
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnConfirm.setBounds(390, 310, 160, 30);
        
        add(btnConfirm);

        btnBack = new JButton("Volver");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnBack.setBounds(110, 310, 80, 30);
        add(btnBack);

        btnCancel = new JButton("Cancelar");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnCancel.setBounds(200, 310, 180, 30);
        add(btnCancel);
        
        
        
        btnBack.addActionListener(e -> reservationController.goStep2());
        btnCancel.addActionListener(e -> HomeController.goHome());
        btnConfirm.addActionListener(e -> reservationController.confirm());
    }
}
