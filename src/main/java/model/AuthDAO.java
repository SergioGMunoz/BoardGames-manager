package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
	Connection conn=ConnectionDB.getConnection();
	
	// Devuelve si el usuario existe en la BBDD
	public boolean userExists(String mail, String pwd) {
		try {
			PreparedStatement st = conn.prepareStatement(
					"SELECT COUNT(*) as num_users FROM users WHERE mail = ? AND password = ?");
			st.setString(1, mail);
			st.setString(2, pwd);
			ResultSet rs = st.executeQuery();
			 if (rs.next()) {
				 int num_users = rs.getInt("num_users");
				 System.out.println(num_users);
                 return num_users > 0;
             }
			
		}catch (SQLException e) {
            System.err.println("❌ Error al comprobar usuario");
            e.printStackTrace();
        }
		return false;
	}
}
