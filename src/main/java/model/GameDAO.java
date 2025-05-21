package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import utils.Debugger;

public class GameDAO {
	Connection conn=ConnectionDB.getConnection();
	
	// Obtiene las diferentes categorias de los juegos posibls
	public ArrayList<String> getAllCategories() {
	    ArrayList<String> categories = new ArrayList<>();
	    String query = "SELECT DISTINCT name FROM CATEGORIES";

	    try {
	    	PreparedStatement st = conn.prepareStatement(query);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            categories.add(rs.getString("name"));
	        }
	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al obtener categorías.");
	        e.printStackTrace();
	    }

	    return categories;
	}
	
	// Obtiene todos los numeros de jugadores posibles en un array de strings
	public ArrayList<String> getAllPlayerCounts() {
	    ArrayList<String> numbers = new ArrayList<>();
	    String query = "SELECT MIN(min_players) AS min, MAX(max_players) AS max FROM GAMES";

	    try {
	    	 PreparedStatement st = conn.prepareStatement(query);
	         ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            int min = rs.getInt("min");
	            int max = rs.getInt("max");

	            for (int i = min; i <= max; i++) {
	                numbers.add(String.valueOf(i));
	            }
	        }
	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al obtener rangos de jugadores.");
	        e.printStackTrace();
	    }

	    return numbers;
	}

	// Obtiene una lista de juegos segun los fitros aportados, si son null se ignoran
	public ArrayList<Object[]> getFilteredGames(String name, Integer jugadores, String type, String orden, String reservationDate, String timeStart) {
	    ArrayList<Object[]> games = new ArrayList<>();
	    boolean reservationFiltersOn = (reservationDate != null && timeStart != null);

	    String query =
	        "SELECT G.id, G.name, MIN(C.name) AS type, G.min_players, G.max_players, " +
	        "G.duration, G.min_age, G.src_img " +
	        "FROM GAMES G " +
	        "LEFT JOIN GAME_CATEGORY GC ON G.id = GC.id_game " +
	        "LEFT JOIN CATEGORIES C ON GC.id_category = C.id ";

	    if ("Más Jugados".equals(orden)) {
	        query += "LEFT JOIN RESERVATIONS R ON G.id = R.id_game ";
	    }

	    if (reservationFiltersOn) {
	        Debugger.print("Aplicando filtros de reserva");
	        query += "WHERE G.id NOT IN ( " +
	                 "SELECT id_game FROM RESERVATIONS " +
	                 "WHERE reservation_date = ? " +
	                 "AND NOT (time_end <= ? OR time_start >= LEAST(ADDTIME(?, G.duration), '22:00:00')) " +
	                 ") ";
	    } else {
	        query += "WHERE 1=1 ";
	        Debugger.print("Sin aplicar filtros de reserva");
	    }

	    if (name != null && !name.isEmpty()) {
	        query += "AND G.name LIKE ? ";
	    }

	    if (jugadores != null) {
	        query += "AND G.min_players <= ? AND G.max_players >= ? ";
	    }

	    if (type != null && !type.isEmpty()) {
	        query += "AND C.name = ? ";
	    }

	    query += "GROUP BY G.id, G.name, G.min_players, G.max_players, G.duration, G.min_age, G.src_img ";

	    if (orden != null) {
	        switch (orden) {
	            case "Más Jugados":
	                query += "ORDER BY COUNT(R.id) DESC ";
	                break;
	            case "Duración asc":
	                query += "ORDER BY G.duration ASC ";
	                break;
	            case "Duración desc":
	                query += "ORDER BY G.duration DESC ";
	                break;
	            case "Edad asc":
	                query += "ORDER BY G.min_age ASC ";
	                break;
	            case "Edad desc":
	                query += "ORDER BY G.min_age DESC ";
	                break;
	        }
	    }

	    try {
	        PreparedStatement st = conn.prepareStatement(query);
	        int index = 1;

	        if (reservationFiltersOn) {
	            st.setString(index++, reservationDate);
	            st.setString(index++, timeStart);
	            st.setString(index++, timeStart); 
	        }

	        if (name != null && !name.isEmpty()) {
	            st.setString(index++, "%" + name + "%");
	        }

	        if (jugadores != null) {
	            st.setInt(index++, jugadores);
	            st.setInt(index++, jugadores);
	        }

	        if (type != null && !type.isEmpty()) {
	            st.setString(index++, type);
	        }

	        Debugger.print("QUERY:");
	        Debugger.print(query);

	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            Object[] row = new Object[]{
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("type"),
	                rs.getInt("min_players"),
	                rs.getInt("max_players"),
	                rs.getTime("duration").toString(),
	                rs.getInt("min_age"),
	                rs.getString("src_img")
	            };
	            games.add(row);
	        }

	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al buscar juegos con filtros");
	        e.printStackTrace();
	    }

	    return games;
	}
	
	// Devuelve la duracion de un juego en string HH:mm:ss por su ID
	public String getGameDurationByID(int id) {
	    String duration = null;
	    String sql = "SELECT duration FROM games WHERE id = ?";

	    try {
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setInt(1, id);
	        try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                duration = rs.getString("duration");
	            }
	        }
	    } catch (SQLException e) {
	    	Debugger.printErr("Error SQL al obtener la duracion del juego id -> " + id);
	        e.printStackTrace(); 
	    }
	    return duration;
	}



}
