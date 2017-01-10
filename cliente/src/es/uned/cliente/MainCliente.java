package es.uned.cliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import es.uned.common.Gui;
import es.uned.common.IServidor;
import es.uned.common.Mensaje;


public class MainCliente {

	private static int miSesion = 0;
	private static IServidor servidor;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		servidor = (IServidor)registry.lookup("Pepito");
		
		gui();
	}
	
	private static void gui() throws RemoteException {
		int opt = 0;
		
		do {
			opt = Gui.menu("Menu Principal", 
							new String[]{ "Autenticarse", 
										  "Agregar Contacto", 
										  "Enviar Mensaje",
										  "Recibir Mensajes",
										  "Salir" });
			
			switch (opt) {
				case 0: autenticarse(); break;
				case 1: agregarContacto(); break;
				case 2: enviarMensaje(); break;
				case 3: recibirMensajes(); break;
			}
		}
		while (opt != 4);
	}
	
	private static void autenticarse() throws RemoteException {
		String nombre = Gui.input("Autenticarse", "Ingrese su nombre: ");
		
		if (nombre != null && !nombre.isEmpty()) {
			//Primera llamada remota:
			miSesion = servidor.autenticar(nombre);
		}
	}
	
	private static void agregarContacto() throws RemoteException {
		String contacto = Gui.input("Agregar Contacto", "Ingrese el contacto: ");
		
		if (contacto != null && !contacto.isEmpty()) {
			int suSesion = servidor.agregar(contacto, miSesion);
			System.out.println("La sesion de " + contacto + " es " + suSesion);
		}
	}
	
	private static void recibirMensajes() throws RemoteException {
		System.out.println("=== Mensajes recibidos ===");
		
		List<Mensaje> mensajes = servidor.recibir(miSesion);
		
		for (Mensaje mensaje : mensajes) {
			System.out.println("De " + mensaje.getRemitente());
			System.out.println("\t" + mensaje.getCuerpo() + "\n");
		}
		
		System.out.println();
		
		servidor.limpiarBuffer(miSesion);
	}
	
	private static void enviarMensaje() throws RemoteException {
		String opts[] = Gui.input("Enviar Mensaje", 
								  new String[]{ "Ingrese la sesion del contacto: ",
												"Ingrese el mensaje: "});
		
		int suSesion = Integer.parseInt(opts[0]);
		String mensaje = opts[1];
		
		servidor.enviar(mensaje, miSesion, suSesion);
	}
}
