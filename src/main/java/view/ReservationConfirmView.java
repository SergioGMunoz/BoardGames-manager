package view;

import javax.swing.*;
import java.awt.*;

public class ReservationConfirmView extends JPanel {
    private JLabel lbTitle;
    private JTextField tfPlayers;
    private JTextField tfDay;
    private JTextField tfMonth;
    private JTextField tfYear;
    private JTextField tfGame;

    private JButton btnConfirm;
    private JButton btnBack;
    private JButton btnCancel;

    public ReservationConfirmView() {
        setLayout(null);
        setBackground(new Color(204, 255, 235));

        lbTitle = new JLabel("Confirmación de Reserva", SwingConstants.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setBounds(0, 94, 640, 30);
        add(lbTitle);

        JLabel lbPlayers = new JLabel("Nº de Jugadores:");
        lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbPlayers.setBounds(130, 164, 150, 20);
        add(lbPlayers);

        tfPlayers = new JTextField();
        tfPlayers.setEditable(false);
        tfPlayers.setBounds(290, 164, 210, 20);
        add(tfPlayers);

        JLabel lbDate = new JLabel("Fecha:");
        lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbDate.setBounds(130, 204, 59, 20);
        add(lbDate);

        tfYear = new JTextField();
        tfYear.setEditable(false);
        tfYear.setBounds(290, 206, 53, 20);
        add(tfYear);

        tfMonth = new JTextField();
        tfMonth.setEditable(false);
        tfMonth.setBounds(353, 206, 75, 20);
        add(tfMonth);

        tfDay = new JTextField();
        tfDay.setEditable(false);
        tfDay.setBounds(441, 206, 59, 20);
        add(tfDay);

        JLabel lbGame = new JLabel("Juego Seleccionado:");
        lbGame.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbGame.setBounds(130, 254, 150, 20);
        add(lbGame);

        tfGame = new JTextField();
        tfGame.setEditable(false);
        tfGame.setBounds(300, 256, 200, 20);
        add(tfGame);

        btnConfirm = new JButton("Confirmar y Descargar");
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnConfirm.setBounds(390, 312, 160, 30);
        add(btnConfirm);

        btnBack = new JButton("Volver");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnBack.setBounds(110, 312, 80, 30);
        add(btnBack);

        btnCancel = new JButton("Cancelar e ir a Inicio");
        btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnCancel.setBounds(200, 312, 180, 30);
        add(btnCancel);
    }
}
