package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Debugger;

public class ReservationDAO {
	Connection conn=ConnectionDB.getConnection();

	// Metodo que devuelve si el jugador ya tiene una reserva para esa fecha y hora de inicio
	public boolean getUserReservationBusy(int userId, String reservationDate, String timeStart) {
	    try  {
	    	PreparedStatement st = conn.prepareStatement(
	    		    "SELECT COUNT(*) FROM RESERVATIONS " +
	    		    "WHERE id_user = ? " +
	    		    "AND reservation_date = ? " +
	    		    "AND ? >= time_start " +
	    		    "AND ? < time_end"
	    		);
	        st.setInt(1, userId);
	        st.setString(2, reservationDate);
	        st.setString(3, timeStart);
	        st.setString(4, timeStart);

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        Debugger.printErr("Error al verificar solapamiento de reserva");
	        e.printStackTrace();
	    }

	    return false;
	}
	
	// Metodo anterior sobreescrito: 
	//Comprueba si el intervalo entre startTime y endTime el user no tiene reservas
	public boolean getUserReservationBusy(int userId, String reservationDate, String timeStart, String timeEnd) {
		Debugger.print("✅ Usando versión con hora de fin");
	    try {
	        PreparedStatement st = conn.prepareStatement(
	            "SELECT COUNT(*) FROM RESERVATIONS " +
	            "WHERE id_user = ? " +
	            "AND reservation_date = ? " +
	            "AND (time_start < ? AND time_end > ?)"
	        );
	        st.setInt(1, userId);
	        st.setString(2, reservationDate);
	        st.setString(3, timeEnd);   
	        st.setString(4, timeStart); 

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; 
	        }
	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al verificar solapamiento de reserva user");
	        e.printStackTrace();
	    }

	    return false;
	}
	
	//Metodo que inserta la reserva y devuelve si ha sido insertada correctamente
	public boolean insertReservation(Object[] data) {
		if (data == null || data.length < 6) {
			Debugger.printErr("No hay datos suficientes para la inserción");
	        return false;
	    }

	    String sql = "INSERT INTO RESERVATIONS (id_user, id_game, num_players, reservation_date, time_start, time_end) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	    	PreparedStatement st = conn.prepareStatement(sql);
	        st.setInt(1, (int) data[0]);           
	        st.setInt(2, (int) data[1]);           
	        st.setInt(3, (int) data[2]);            
	        st.setDate(4, java.sql.Date.valueOf((String) data[3]));  
	        st.setTime(5, java.sql.Time.valueOf((String) data[4]));  
	        st.setTime(6, java.sql.Time.valueOf((String) data[5]));

	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        Debugger.printErr("Error al insertar una reserva en la base de datos");
	        e.printStackTrace();
	        return false;
	    }
		
	}
	
	


}
