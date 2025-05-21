package model;

import java.time.LocalDateTime;
import java.util.Arrays;

import utils.Debugger;

public class Reservation {
    private static int userId;
    private static int gameId;
    private static int numPlayers;
    private static String reservationDate; 
    private static String timeStart;     
    private static String timeEnd;  
    private static String gameName;
    
    public static void clearReservation() {
        userId = 0;
        gameId = 0;
        numPlayers = 0;
        reservationDate = null;
        timeStart = null;
        timeEnd = null;
    }
    	
    // Getters
    public static Object[] getAllData() {
        Object[] data = new Object[] {
            userId,
            gameId,
            numPlayers,
            reservationDate,
            timeStart,
            timeEnd
        };

        Debugger.print("Reservation data → " + Arrays.toString(data));

        return data;
    }


    public static int getUserId() {
        return userId;
    }

    public static int getGameId() {
        return gameId;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static String getReservationDate() {
        return reservationDate;
    }

    public static String getTimeStart() {
        return timeStart;
    }

    public static String getTimeEnd() {
        return timeEnd;
    }
    
    public static String getGameName() {
    	return gameName;
    }
    // Setters
    public static void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return;

        Reservation.reservationDate = dateTime.toLocalDate().toString();
        
        // Enlace investigación: https://es.stackoverflow.com/questions/169527/formatear-localtime
        // Añade el tiempo de inicio
        Reservation.timeStart = dateTime.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    
    public static void setUserId(int userId) {
        Reservation.userId = userId;
    }

    public static void setGameId(int gameId) {
        Reservation.gameId = gameId;
    }

    public static void setNumPlayers(int numPlayers) {
        Reservation.numPlayers = numPlayers;
    }

    public static void setReservationDate(String reservationDate) {
        Reservation.reservationDate = reservationDate;
    }

    public static void setTimeStart(String timeStart) {
        Reservation.timeStart = timeStart;
    }

    public static void setTimeEnd(String timeEnd) {
        Reservation.timeEnd = timeEnd;
    }
    
    public static void setGameName(String gameName) {
		Reservation.gameName = gameName;
	}

}
