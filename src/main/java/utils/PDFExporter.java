package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFExporter {

	public static boolean exportReservation(String gameName, Object[] data, String filePath) {
	    if (data == null || data.length < 6) {
	        Debugger.printErr("❌ Datos de reserva incompletos.");
	        return false;
	    }

	    Document document = new Document();

	    try {
	        PdfWriter.getInstance(document, new FileOutputStream(filePath));
	        document.open();

		     document.add(new Paragraph("Reserva confirmada para " + Session.getName() + ": ")); 
		     document.add(new Paragraph("Juego reservado: " + gameName));
		     document.add(new Paragraph("Número de jugadores: " + data[2]));
		     document.add(new Paragraph("Fecha: " + data[3]));
		     document.add(new Paragraph("Hora de inicio: " + data[4]));
		     document.add(new Paragraph("Hora de fin: " + data[5]));
	
		     document.close();

	        return true;

	    } catch (DocumentException | IOException e) {
	        Debugger.printErr("❌ Error al generar el PDF.");
	        e.printStackTrace();
	        return false;
	    }
	}

}
