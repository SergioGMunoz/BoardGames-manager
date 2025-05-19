package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthDAO {
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
            System.err.println("❌ Error al comprobar usuario");
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
	        System.err.println("❌ Error al comprobar si el correo existe");
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
	        System.out.println("FILAS: " + filas);
	        return filas >= 1;

	    } catch (SQLException e) {
	    	System.out.println("Error insert SQL");
	        System.err.println("❌ Error SQL al registrar el usuario: " + e.getMessage());
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
	        System.err.println("❌ Error SQL al buscar user con mail: " + mail);
	        e.printStackTrace();
	    }
	    return dataUser;
	}

}
