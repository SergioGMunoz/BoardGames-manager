package utils;

import java.util.ArrayList;

public class Session {
	
    private static boolean activeSession = false;
    private static Integer id;
    private static String name;
    private static String mail;
    private static Boolean mailVerified;
    private static String password;
    private static java.sql.Date regDate;

    // Inicia la sesion rellenando los datos con los del usuario
    public static boolean startSession(ArrayList<Object> userData) {
        if (activeSession) {
            System.err.println("❌ Sesión ya activa para user-id: " + userData.get(0) );
            return false;
        } else {
        	try {
        		id = (Integer) userData.get(0);
        		name = (String) userData.get(1);
        		mail = (String) userData.get(2);
        		mailVerified = (Boolean) userData.get(3);
        		password = (String) userData.get(4);
        		regDate = (java.sql.Date) userData.get(5);
        	}catch(Exception e) {
        		System.err.println("❌ Error insesperado al crear sesion");
        		e.printStackTrace();
        		return false;
        	}
            
            activeSession = true;
            return true;
        }
    }
    
    // Elimina los datos del usuario de la sesion
    public static boolean endSession() {
        if (!activeSession) {
            System.err.println("❌ No hay ninguna sesión activa");
            return false;
        } else {
            id = null;
            name = null;
            mail = null;
            mailVerified = null;
            password = null;
            regDate = null;

            activeSession = false;
            return true;
        }
    }

    
    // GETTERS y SETTERS...
    
	public static boolean isActiveSession() {
		return activeSession;
	}

	public static Integer getId() {
		return id;
	}

	public static String getName() {
		return name;
	}

	public static String getMail() {
		return mail;
	}

	public static Boolean getMailVerified() {
		return mailVerified;
	}

	public static String getPassword() {
		return password;
	}

	public static java.sql.Date getRegDate() {
		return regDate;
	}

	public static void setName(String newName) {
		name = newName;
	}

   
}
