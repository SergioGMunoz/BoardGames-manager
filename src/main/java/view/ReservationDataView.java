package view;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controller.HomeController;
import controller.ReservationController;


public class ReservationDataView extends JPanel implements ErrorDisplayable {
    private static final long serialVersionUID = 1L;

    private JSpinner spPlayers;
    private JButton btnHome;
    private JButton btnNext;
    private JLabel lbError;
    private JDateChooser spDay;
    private JSpinner spHour;
    private JSpinner spMinute;
    private ReservationController reservationController;

    public ReservationDataView(ReservationController reservationController) {
        this.reservationController = reservationController;
        init();
    }

    @Override
    public void showError(String msg) {
        lbError.setText(msg);
    }

    @Override
    public void clearMsg() {
        lbError.setText("");
    }
    
    private void updateBtnNext() {
    	btnNext.setEnabled(spDay.getDate() != null);
    }
    
    // Getters para datos de los campos
    public LocalDateTime getDateTime() {
        Date date = spDay.getDate();
        if (date == null) return null;
        
        // Cambiar a LocalDate
        // Enlace conocimiento: https://es.stackoverflow.com/questions/13776/pasar-de-java-util-date-a-java-time-localdate
        LocalDate localDate = date.toInstant()
                                   .atZone(java.time.ZoneId.systemDefault())
                                   .toLocalDate();

        int hour = (int) spHour.getValue();
        int minute = (int) spMinute.getValue();
        
        LocalTime localTime = LocalTime.of(hour, minute);

        return LocalDateTime.of(localDate, localTime);
    }


    public int getPlayers() {
        return (int) spPlayers.getValue();
    }

    public void init() {
        setLayout(null);
        setBackground(new Color(204, 255, 235));

        JLabel lbTitle = new JLabel("Reservar un juego");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lbTitle.setBounds(202, 81, 300, 30);
        add(lbTitle);

        JLabel lbPlayers = new JLabel("Número de jugadores:");
        lbPlayers.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbPlayers.setBounds(162, 151, 150, 20);
        add(lbPlayers);

        spPlayers = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spPlayers.setBounds(322, 151, 50, 20);
        add(spPlayers);

        JLabel lbDate = new JLabel("Fecha:");
        lbDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbDate.setBounds(162, 191, 160, 20);
        add(lbDate);

        spDay = new JDateChooser();
        spDay.setMinSelectableDate(new Date()); // Minimo selecionar hoy
        spDay.setBounds(242, 191, 150, 20);
        add(spDay);

        JLabel lbHour = new JLabel("Hora:");
        lbHour.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbHour.setBounds(162, 231, 104, 20);
        add(lbHour);

        spHour = new JSpinner(new SpinnerNumberModel(10, 10, 21, 1)); // Horas de 10-21
        spHour.setBounds(242, 231, 60, 20);
        add(spHour);

        spMinute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1)); // Minutos de 0-59
        spMinute.setBounds(312, 231, 60, 20);
        add(spMinute);

        JLabel lbGameDuration = new JLabel("La hora de fin se calcula según la duración del juego.");
        lbGameDuration.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbGameDuration.setBounds(162, 271, 400, 20);
        add(lbGameDuration);

        JLabel lbOpeningHours = new JLabel("Horario de la tienda: 10:00 – 22:00");
        lbOpeningHours.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbOpeningHours.setBounds(162, 291, 300, 20);
        add(lbOpeningHours);

        btnHome = new JButton("Volver al inicio");
        btnHome.setBounds(160, 339, 132, 25);
        add(btnHome);

        btnNext = new JButton("Siguiente paso");
        btnNext.setEnabled(false);
        btnNext.setBounds(340, 339, 132, 25);
        add(btnNext);

        lbError = new JLabel("", SwingConstants.CENTER);
        lbError.setForeground(Color.RED);
        lbError.setBounds(158, 314, 314, 14);
        add(lbError);

        btnHome.addActionListener(e -> HomeController.goHome());
        btnNext.addActionListener(e -> reservationController.tryNext());
        
        spDay.getDateEditor().addPropertyChangeListener("date", evt -> {
            updateBtnNext();
        });
    }

}
