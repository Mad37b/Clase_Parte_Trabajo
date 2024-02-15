import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FicheroCliente {
	
	
	public Cliente leerFichero() {
		Cliente cliente = null;
		try (ObjectInputStream miFichero = new ObjectInputStream(new FileInputStream("clientes.dat"))) {
			// Cuando no haya mas objetos saltara la excepcion EOFException
			
			while (true) {
				cliente = (Cliente) miFichero.readObject();
				// Cada aux tendria los datos del Usuario

				System.out.println(cliente.getNombre());
				System.out.println(cliente.getNif());
				System.out.println(cliente.getCorreo());
				System.out.println(cliente.getTelefono());
			}
		} catch (ClassNotFoundException e) {
		} catch (EOFException e) {
		} catch (IOException e) {
		}
		
		
		return cliente;
	}	

	/** Metodo 1 : Crear fichero **/

	public void crearFichero(Cliente cliente) {
		
		
		// Escribir fichero por objeto

		try (ObjectOutputStream miFichero = new ObjectOutputStream(new FileOutputStream("Clientes.dat"))) {

			miFichero.writeObject(cliente);
			
		

		} catch (IOException write) {
			System.out.println("ERROR 01: No se da grabado la lista");
		}

		// Leer fichero por objeto
		
		

	}
	
}
