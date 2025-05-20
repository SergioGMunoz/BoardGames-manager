package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Debugger;

public class UserDAO {
	Connection conn=ConnectionDB.getConnection();
	
	// Devuelve si el usuario y contraseña en la BBDD
	public boolean userExists(String mail, String pwd) {
		try {
			PreparedStatement st = conn.prepareStatement(
					"SELECT COUNT(*) as num_users FROM users WHERE mail = ? AND password = ?");
			st.setString(1, mail);
			st.setString(2, pwd);
			ResultSet rs = st.executeQuery();
			 if (rs.next()) {
				 int num_users = rs.getInt("num_users");
                 return num_users > 0;
             }
			
		}catch (SQLException e) {
            Debugger.printErr("Error al comprobar usuario");
            e.printStackTrace();
        }
		return false;
	}
	
	//Devuelve si el mail ya esta registrado en la BBDD
	public boolean mailExists(String mail) {
	    try {
	        PreparedStatement st = conn.prepareStatement(
	                "SELECT COUNT(*) as mails FROM users WHERE mail = ?");
	        st.setString(1, mail);
	        ResultSet rs = st.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt("mails");
	            return count > 0;
	        }
	        
	    } catch (SQLException e) {
	        Debugger.printErr("Error al comprobar si el correo existe");
	        e.printStackTrace();
	    }
	    return false;
	}

	//Devuelve si el usuario ha sido registrado en la BBDD
	public boolean registerUser(String name, String mail, String hashedPwd) {
	    String sql = "INSERT INTO users (name, mail, password) VALUES (?, ?, ?)";

	    try (PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setString(1, name);
	        st.setString(2, mail.toLowerCase()); 
	        st.setString(3, hashedPwd);

	        int filas = st.executeUpdate();
	        Debugger.print("FILAS: " + filas);
	        return filas >= 1;

	    } catch (SQLException e) {
	    	Debugger.print("Error insert SQL");
	        Debugger.printErr("Error SQL al registrar el usuario: " + e.getMessage());
	        return false;
	    }
	}
	
	// Devuelve la información de un usuario cuya ID
	public ArrayList<Object> getUserByMail(String mail) {
		ArrayList<Object> dataUser = null;
		try {
	        PreparedStatement st = conn.prepareStatement(
	                "SELECT * FROM users WHERE mail = ?");
	        st.setString(1, mail);
	        ResultSet rs = st.executeQuery();
	        
	        if (rs.next()) {
	        	dataUser = new ArrayList<>();
	        	dataUser.add(rs.getInt("id"));
	            dataUser.add(rs.getString("name"));
	            dataUser.add(rs.getString("mail")); 
	            dataUser.add(rs.getBoolean("mail_verified"));
	            dataUser.add(rs.getString("password"));
	            dataUser.add(rs.getDate("reg_date"));
	        }
	        
	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al buscar user con mail: " + mail);
	        e.printStackTrace();
	    }
	    return dataUser;
	}
	
	public ArrayList <Object> getUserDataByID(int id) {
		ArrayList<Object> dataUser = null;
		String query = "SELECT \r\n"
				+ "    name, mail, \r\n"
				+ "    (SELECT COUNT(*) FROM RESERVATIONS WHERE id_user=?) AS games_played, \r\n"
				+ "    (\r\n"
				+ "        COALESCE((\r\n"
				+ "            SELECT GAMES.name\r\n"
				+ "            FROM RESERVATIONS \r\n"
				+ "            JOIN GAMES ON RESERVATIONS.id_game = GAMES.id \r\n"
				+ "            WHERE id_user=?\r\n"
				+ "            GROUP BY id_game \r\n"
				+ "            ORDER BY COUNT(*) DESC \r\n"
				+ "            LIMIT 1\r\n"
				+ "        ), '-')\r\n"
				+ "    ) AS top_game, \r\n"
				+ "    reg_date\r\n"
				+ "FROM USERS \r\n"
				+ "WHERE id=?;";
		try{			
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, id);
			st.setInt(2, id);
			st.setInt(3, id);
			
			ResultSet rs = st.executeQuery();
			dataUser = new ArrayList();
	        
	        if (rs.next()) {
	        	dataUser.add(rs.getString("name")); 
	            dataUser.add(rs.getString("mail")); 
	            dataUser.add(rs.getInt("games_played"));
	            dataUser.add(rs.getString("top_game"));
	            dataUser.add(rs.getDate("reg_date"));
	        }
			
		}catch(SQLException e) {
			Debugger.printErr("Error SQL al buscar user por id: " + id);
	        e.printStackTrace();
		}
		return dataUser;
	}
	
	// Actualiza el nombre de un usuario por su ID
	public boolean updateUserNameByID(int id, String newUsername) {
	    String sql = "UPDATE users SET name = ? WHERE id = ?";
	    
	    try {
	    	PreparedStatement st = conn.prepareStatement(sql);
	        st.setString(1, newUsername);
	        st.setInt(2, id);

	        int rowsUpdated = st.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al actualizar el nombre de usuario id: " + id);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// Elimina el user de la BBDD y devuelve si lo ha conseguido
	public boolean deleteUserByID(int id) {
	    try {
	    	String sql = "DELETE FROM users WHERE id = ?";
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setInt(1, id);
	        return st.executeUpdate() > 0;

	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al eliminar el usuario con id: " + id);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// Actualiza la contraseña de un usuario por su id
	public boolean updatePasswordByID(int id, String newPassword) {
	    try {
	    	String sql = "UPDATE users SET password = ? WHERE id = ?";
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setString(1, newPassword);
	        st.setInt(2, id);

	        int rowsUpdated = st.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException e) {
	        Debugger.printErr("Error SQL al actualizar la contraseña del usuario con id: " + id);
	        e.printStackTrace();
	        return false;
	    }
	}

	


}
