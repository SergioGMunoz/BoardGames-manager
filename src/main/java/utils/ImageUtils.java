package utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageUtils {
	
	// Devuelve una imagen encotrada en el path con las dimensiones with y height
    public static ImageIcon loadImageIcon(String path, int width, int height) {
        if (path == null) return null;

        URL url = ImageUtils.class.getClassLoader().getResource(path);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("❌ Imagen no encontrada: " + path);
            return null;
        }
    }
}
