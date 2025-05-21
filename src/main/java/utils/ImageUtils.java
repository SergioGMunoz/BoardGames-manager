package utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageUtils {
	
	// Devuelve una imagen encotrada en el path con las dimensiones with y height
    public static ImageIcon loadImageIcon(String path, int width, int height) {
        if (path == null || path.trim().length()<=0) return null;
        
        // Busca en el proyecto la ruta absoluta y la carga como URL
        URL url = ImageUtils.class.getClassLoader().getResource(path);
        
        if (url != null) {
        	// Crea una imagen
            ImageIcon icon = new ImageIcon(url);
            // La escala al tamaño solicitado
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
           Debugger.printErr("Imagen no encontrada: " + path);
            return null;
        }
    }
}
