package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	// Devuelve todas las reservas FUTURAS de un usuario
	public ArrayList<Object[]> getAllFutureReservationsByUserID(Integer id) {
	    ArrayList<Object[]> list = new ArrayList<>();

	    String query = "SELECT \r\n"
	    		+ "	            r.id, \r\n"
	    		+ "	            g.name, \r\n"
	    		+ "	            r.reservation_date, \r\n"
	    		+ "				r.num_players, \r\n"
	    		+ "	            r.time_start, \r\n"
	    		+ "	            r.time_end\r\n"
	    		+ "	        FROM RESERVATIONS r\r\n"
	    		+ "	        JOIN GAMES g ON r.id_game = g.id\r\n"
	    		+ "	        WHERE r.id_user = ?\r\n"
	    		+ "	        AND (\r\n"
	    		+ "	            r.reservation_date > CURDATE() OR \r\n"
	    		+ "	            (r.reservation_date = CURDATE() AND r.time_start > CURTIME())\r\n"
	    		+ "	        )\r\n"
	    		+ "	        ORDER BY r.reservation_date, r.time_start";

	    try  {
	    	PreparedStatement st = conn.prepareStatement(query);
	    	st.setInt(1, id);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            int reservationId = rs.getInt("id");
	            String gameName = rs.getString("name");
	            String date = rs.getString("reservation_date");
	            int numPlayers = rs.getInt("num_players");
	            String timeRange = rs.getString("time_start") + " - " + rs.getString("time_end");

	            list.add(new Object[] { reservationId, gameName, numPlayers,  date, timeRange });
	        }

	    } catch (SQLException e) {
	        Debugger.printErr("❌ Error al obtener reservas futuras del usuario");
	        e.printStackTrace();
	    }

	    return list;
	}
	
	// Elimina la reserva con el id dado, devuelve si lo ha hecho correctamente
	public boolean deleteReservationById(int id) {
	    String sql = "DELETE FROM RESERVATIONS WHERE id = ?";

	    try  {
	    	PreparedStatement st = conn.prepareStatement(sql);
	        st.setInt(1, id);
	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        Debugger.printErr("❌ Error SQL al eliminar la reserva con ID: " + id);
	        e.printStackTrace();
	        return false;
	    }
	}

	
	


}
