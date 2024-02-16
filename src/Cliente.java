import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cliente extends JFrame implements Serializable  {

	/** JavaDoc **/

	/**
	 * @author ULISES III
	 * 
	 * @version 1.1
	 * 
	 * @Descripcion:
	 * 
	 *               En esta clase Cliente se Realiza y que genere un fichero con la
	 *               lista de partes realizados y tenga la posibilidad de Generar,
	 *               Consultar, Ver con detalle la lista de clientes
	 * 
	 *               // NIF/Documento identidad (Validado) // b. Nombre // c. Correo
	 *               electrónico (validado) // d. Teléfono de contacto
	 * 
	 * @Modulos: Crear fichero , generar datos por teclado , grabar fichero
	 * 
	 */

	// Atributos de la clase
	
	private JPanel panel ;
	private JTextField textNombre;
	private JTextField textNif;
	private JTextField textCorreo;
	private JTextField textTelefono;
	
	
	private JButton buttonAlta;

	// Lista Array
	static List<Cliente> listaClientes = new ArrayList<>();

	// String

	private String nombre;
	private String nif;
	private String correo;
	private String telefono;

	private static Scanner teclado = new Scanner(System.in);

	// Pattern

	private Pattern nifPattern = Pattern.compile("^\\d{8}\\w[TRWAGMYFPDXBNJZSQVHLCKE]$");
	private Pattern telefonoPattern = Pattern.compile("^\\d{3}\\s\\d{3}\\s\\d{3}$");

	// fichero
	private String ficheroCliente;

	public Cliente() {
//  Nunca llamar a los metodos de la clase por evitar un bucle infinito
		// verificarCliente();
		//insertarDatos();
		
		setTitle("Alta de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		getContentPane().setLayout(null);
	};

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertarDatos() {
		boolean salir = false;
		System.out.println("Introduce los datos del cliente : Nombre, Nif, Correo, Telefono");
		Cliente cliente = new Cliente();
		while (!salir) {
			
			System.out.println("Nombre : ");

			nombre = teclado.next();
			listaClientes.add(cliente);

			System.out.println(" Nif : ");
			nif = teclado.next();
			listaClientes.add(cliente);

			Matcher matcherNif = nifPattern.matcher(nif);

			// Verificar Nif

			if (matcherNif.find()) {
				System.out.println("Pattern found at index: " + matcherNif.start());
			} else {
				System.out.println("Pattern not found");
			}

			System.out.println(" Correo : ");
			correo = teclado.next();
			listaClientes.add(cliente);

			System.out.println(" Telefono : ");
			telefono = teclado.next();

			Matcher matcherTelefono = telefonoPattern.matcher(telefono);
			
			if (matcherTelefono.find()) {
				System.out.println("Nif valido en el  index: " + matcherTelefono.start());
			} else {
				System.out.println("Telefono no válido");
				return;
			}
			
			listaClientes.add(cliente);

			// metodo para seguir o no introduciendo datos

			System.out.println(" - ¿ Quieres dejar de introducit Datos del cliente ? (s - n) ");

			String continuar = teclado.next();

			if (!continuar.equalsIgnoreCase("s")) {
				salir = true;
			}

		}
	}

	public List<Cliente> archivoCliente(List<Cliente> listaClientes) {
		List<String> propiedadesClientes = new ArrayList<>();
		for (Cliente cliente : listaClientes) {

			propiedadesClientes.add(cliente.getNombre());
			propiedadesClientes.add(cliente.getNif());
			propiedadesClientes.add(cliente.getCorreo());
			propiedadesClientes.add(cliente.getTelefono());

		}
		return listaClientes;
	}

	/** Metodo 2 : Insertar Datos por teclado **/

	/**
	 * public void verificarCliente() {
	 * 
	 * // Verificar Datos del cliente
	 * 
	 * if ( ver si cliente existe) { System.out.println("el cliente verificado
	 * existe"); }else ( noexist ){
	 * 
	 * System.out.println(" el cliente no existe , introduce un nuevo cliente ");
	 * break; } }
	 **/

	// devolver la lista del cliente

	// Metodo 3 : Grabar fichero

	// Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return " Cliente{" + "nombre='" + nombre + '\'' + ", nif='" + nif + '\'' + ", correo='" + correo + '\''
				+ ", telefono='" + telefono + '\'' + '}';
	}
	
	// validar Cliente
	
	// boolean 
	private boolean existe;
	public static  boolean ClienteExiste(Cliente nuevoCliente,List<Cliente> listaClientes ) {
		
		if ( !Cliente.existe(nuevoCliente, listaClientes) ) {
			
			verMensaje(" El cliente no se ha dado de alta"); 
			return false;
	}else {
		
		// por JoptionPanel
		verMensaje("El cliente se ha dado de alta");
		// por consola
		return true;
	}
		
}
	
	private static boolean existe(Cliente nuevoCliente, List<Cliente> listaClientes2) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public static String pedirCliente() {
		String pedirClientes = JOptionPane.showInputDialog("Introduce el cliente:");
		verMensaje("Si el cliente existe paso al siguiente campo\n"+
		           "si no existe hago que salte panel de alta cliente");
		     
		return pedirClientes;
	}
	
	// hacer Jframe de mi programa 
	
	// 

	public static void main(String[] arg) {
		Cliente nuevoCliente = new Cliente();
		ClienteExiste(nuevoCliente, listaClientes);
		FicheroCliente fichero = new FicheroCliente();
		nuevoCliente.insertarDatos();
		nuevoCliente.archivoCliente(listaClientes);
		fichero.crearFichero(nuevoCliente);
		System.out.println(nuevoCliente);
		
		

		// Crear instancia de la clase para crear el fichero del cliente
	}


}