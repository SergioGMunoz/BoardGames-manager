package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Debugger;

public class ReservationDAO {
	Connection conn=ConnectionDB.getConnection();

	// Metodo que devuelve si el jugador ya tiene una reserva para esa fecha y hora
	public boolean getUserReservationBusy(int userId, String reservationDate, String timeStart) {
	    String query = "SELECT COUNT(*) FROM RESERVATIONS " +
	                   "WHERE id_user = ? " +
	                   "AND reservation_date = ? " +
	                   "AND ? BETWEEN time_start AND time_end";

	    try  {
	    	PreparedStatement st = conn.prepareStatement(query);
	        st.setInt(1, userId);
	        st.setString(2, reservationDate);
	        st.setString(3, timeStart);

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

}
