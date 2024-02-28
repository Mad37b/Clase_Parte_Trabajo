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
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 */
public class Cliente extends JFrame implements Serializable {

	/** JavaDoc **/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	private JPanel panel;
	private JTextField textNombre;
	private JTextField textNif;
	private JTextField textCorreo;
	private JTextField textTelefono;

	// Lista Array
	static List<Cliente> listaClientes = new ArrayList<>();

	// String

	private  String nombre;
	private static  String nif;
	private static String correo;
	private String telefono;

	// Pattern

	private Pattern nifPattern = Pattern.compile("^\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]$"); // pattern valido , nunca ponerle \\w cuando haya letras en corchetes
	private Pattern telefonoPattern = Pattern.compile("^\\d{3}\\s\\d{3}\\s\\d{3}$");

	// fichero
	// private String ficheroCliente;

	public Cliente() {
		// Nunca llamar a los metodos de la clase por evitar un bucle infinito
		// verificarCliente();
		// insertarDatos();

		setTitle("Alta de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		getContentPane().setLayout(null);

		// JPanel ///////////////////////////////////////////

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		// JLabel Nombre , Nif, Correo, Telefono //////////////////

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelNombre.setBounds(43, 40, 72, 14);
		getContentPane().add(labelNombre);

		JLabel labelNif = new JLabel("Nif");
		labelNif.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelNif.setBounds(45, 78, 46, 14);
		getContentPane().add(labelNif);

		JLabel labelCorreo = new JLabel("Correo");
		labelCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelCorreo.setBounds(43, 129, 61, 14);
		getContentPane().add(labelCorreo);

		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTelefono.setBounds(43, 177, 72, 14);
		getContentPane().add(labelTelefono);

		// Texto Nombre , Nif , Correo , Telefono

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setBounds(204, 39, 120, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textNif = new JTextField();
		textNif.setEditable(false);
		textNif.setColumns(10);
		textNif.setBounds(204, 77, 120, 20);
		getContentPane().add(textNif);

		textCorreo = new JTextField();
		textCorreo.setEditable(false);
		textCorreo.setColumns(10);
		textCorreo.setBounds(204, 128, 120, 20);
		getContentPane().add(textCorreo);

		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setColumns(10);
		textTelefono.setBounds(204, 176, 120, 20);
		getContentPane().add(textTelefono);

		////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton botonGenerarFichero = new JButton("Generar Fichero");
		botonGenerarFichero.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * @Descripcion : Creamos un fichero y un nuevo cliente cuando pulsamos el boton
			 *              " Generar Fichero"
			 **/

			public void mouseClicked(MouseEvent e) {

				try {
					verMensaje("se está intentando crear el fichero ");
					FicheroCliente fichero = new FicheroCliente();
					Cliente nuevoCliente = new Cliente();

					nuevoCliente.insertarDatos();
					nuevoCliente.archivoCliente(listaClientes);
					// nuevoCliente.archivoCliente(listaClientes);

					// validar datos

					if (ClienteExiste(listaClientes)) {
						String verificarCliente = nombre + "--" + nif + "--" + correo + "--" + telefono + "--";

						verMensaje(" el cliente existe , sea creado en fichero");
						verMensaje("Datos del cliente = "+verificarCliente);
						System.out.println("test si cliente existe en el boton   "+nuevoCliente);
						listaClientes.add(nuevoCliente);
						fichero.crearFichero(nuevoCliente);
						
					

					} else {
						verMensaje(" No se ha podido introducir Cliente");
					}
				} catch (Exception f) {
					verMensaje("ERROR 1 : el fichero no se esta creando ");
					f.printStackTrace();
					// TODO: handle exception
				}

			}
		});
		botonGenerarFichero.setBounds(130, 215, 120, 35);
		getContentPane().add(botonGenerarFichero);
	};

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void insertarDatos() {
		boolean salir = false;

		while (!salir) {

			setVisible(true);

			// introducir Nombre

			nombre = JOptionPane.showInputDialog(null, "Introduce un Nombre:");
			textNombre.setText(nombre);

			// introducit Nif

			nif = JOptionPane.showInputDialog(null, " Introduce un Nif");
			textNif.setText(nif);

			Matcher matcherNif = nifPattern.matcher(nif);

			// Verificar Nif

			if (matcherNif.find()) {
				System.out.println("Pattern Nif found at index: " + matcherNif.start());
				System.out.println(" el Nif  valido es " + matcherNif.group());
			} else {
				System.out.println("Pattern Nif not found");
				System.out.println(" el Nif no valido es " + matcherNif.group());
			}

			// introducit Correo

			 correo = JOptionPane.showInputDialog(null, " Introduce un Correo");
			textCorreo.setText(correo); // muestra el correo en el campo de texto
			// añadir el correo a la lista de clientes

			// insertar datos del telefono

			telefono = JOptionPane.showInputDialog(null, " Introduce un telefono");
			textTelefono.setText(telefono);

			Matcher matcherTelefono = telefonoPattern.matcher(telefono);

			// por JOptionPanel
			if (matcherTelefono.find()) {
				verMensaje("telefono valido en el  index: " + matcherTelefono.start());
			} else {
				verMensaje("Telefono no válido");
				return;
			}
			// Añadir los datos 
			

		

			// Metodo para seguir o no introduciendo datos

			int continuar = JOptionPane.showConfirmDialog(null, "¿Quieres Salir?", "Si / No",
					JOptionPane.YES_NO_OPTION);

			if (continuar == JOptionPane.YES_OPTION) {
				salir = true;
			} else {
				salir = false;
			}

		}

	}

	public List<Cliente> archivoCliente(List<Cliente> listaClientes) {
		List<Cliente> nuevoClienteList = new ArrayList<>();
			
		
		for (Cliente cliente : listaClientes) {
			Cliente nuevoClientes=new Cliente();
			
			
			nuevoClientes.setNombre(cliente.getNombre());
			nuevoClientes.setNif(cliente.getNif());
			nuevoClientes.setCorreo(cliente.getCorreo());
			nuevoClientes.setTelefono(cliente.getTelefono());
		
		
		
        nuevoClienteList.add(nuevoClientes);
        System.out.println("test 2 : " );
        System.out.println(nuevoClientes);
		
		
		}return listaClientes;
		
	}

	// Getters & Setters
	public String getNombre() {
		return textNombre.getText();
	}

	public String setNombre(String nombre) {
		this.textNombre.setText(nombre);
		return nombre;
	}

	public String getNif() {

		return textNif.getText();

	}

	public void setNif(String nif) {
		this.nif=nif;
	}

	public String getCorreo() {
		return textCorreo.getText();
	}

	public void setCorreo(String correo) {
		this.correo=correo;
	}

	public String getTelefono() {
		return textTelefono.getText();
	}

	public void setTelefono(String telefono) {
		
		 this.telefono = telefono;
	}

	@Override
	public String toString() {
		return " Cliente{" + "nombre='" + nombre + '\'' + ", nif='" + nif + '\'' + ", correo='" + correo + '\''
				+ ", telefono='" + telefono + '\'' + '}';
	}

	// comprobar cliente existe por el nif por clientes.dat

	public static boolean ClienteExiste(List<Cliente> listaClientes) {
		// validar si su nif existe
		// validar si los datos existen en la lista
		if (!Cliente.existe(nif, listaClientes)) {

			verMensaje(" El cliente Si se ha dado de alta");
			return true;
		} else {

			// por JoptionPanel
			verMensaje("El cliente No se ha dado de alta");
			// por consola
			return false;
		}

	}

	private static boolean existe(String nif, List<Cliente> listaClientes) {
		// TODO Auto-generated method stub
		// Verifica por cada linea 
		
		for ( Cliente cliente : listaClientes) {
			if (cliente.getNif().equals(nif)) {
				System.out.println("El nif del cliente existe");
				return true;
			}
		}
		
		return false;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Metodos de JOptionPanel **/

	private static void verMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public static String pedirCliente() {
		String pedirClientes = JOptionPane.showInputDialog("Introduce el cliente:");
		verMensaje(
				"Si el cliente existe paso al siguiente campo\n" + "si no existe hago que salte panel de alta cliente");

		return pedirClientes;
	}

	// hacer Jframe de mi programa

	//

	public static void main(String[] arg) {
		verMensaje("Introduce los datos del cliente : Nombre, Nif, Correo, Telefono");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}