import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class RegexCliente {
	
	// Instancia de la clase RegexCliente 
	
	RegexCliente Validar = new RegexCliente();
	
	Pattern PatternNif = Pattern.compile("^\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
	Pattern PattenrCorreo = Pattern.compile(null); 
	Pattern PatternTelefono = Pattern.compile ("^\\d{3}\\s\\d{3}\\s\\d{3}$");
	
	
	
	String nif;
	String telefono;
	
	Matcher matcherNif = PatternNif.matcher(nif);
	Matcher matcherTelefono = PatternTelefono.matcher(telefono);
	
	
 public RegexCliente() {
	// TODO Auto-generated constructor stub
	 
	 
	 
		if (matcherNif.find()) {
			System.out.println("Pattern Nif found at index: " + matcherNif.start());
			System.out.println(" el Nif  valido es " + matcherNif.group());
		} else {
			System.out.println("Pattern Nif not found");
			System.out.println(" el Nif no valido es " + matcherNif.group());
		}
		
	

		// por JOptionPanel
		if (matcherTelefono.find()) {
			verMensaje("telefono valido en el  index: " + matcherTelefono.start());
			
		} else {
			verMensaje("Telefono no válido");
			return ;
		}
	 
	 
}
 
 private static void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
 
 boolean telefonoExiste(String t) {
	 
		if (matcherTelefono.find()) {
			verMensaje("telefono valido en el  index: " + matcherTelefono.start());
			return true;
		} else {
			verMensaje("Telefono no válido");
			return false;
		}
	
 }
 
	

	// Verificar Nif



 
 
}
