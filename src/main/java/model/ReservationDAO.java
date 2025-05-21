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
	
	


}
