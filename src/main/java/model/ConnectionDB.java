package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.Debugger;


public class ConnectionDB {
    static Connection connection;


    static public Connection getConnection() {
		return connection;
	}

	public static boolean connect(String user, String pwd, String host, String port, String dbName) {
    	String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			connection = DriverManager.getConnection(url, user, pwd);
			Debugger.print("✔️ Conexión exitosa con la BBDD" + dbName);
			return true;

    	} catch (ClassNotFoundException e) {
			Debugger.printErr("❌ Driver JDBC No encontrado");
			e.printStackTrace();
		} catch (SQLException e) {
			Debugger.printErr("❌ Error al conectarse a la BD");
			e.printStackTrace();
		} catch (Exception e) {
			Debugger.printErr("❌ Error general de Conexión");
			e.printStackTrace();
		}
    	return false;
    }
    
    public static boolean close() {
    	try {
    		connection.close();
    		Debugger.print("✔️ Conexión cerrada correctamente");
    		return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			Debugger.printErr("❌ Error inesperado");
		}
    	return false;
    }
}

