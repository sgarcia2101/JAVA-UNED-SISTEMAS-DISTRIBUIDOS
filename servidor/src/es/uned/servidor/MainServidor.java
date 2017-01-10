package es.uned.servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.uned.common.IServidor;
import es.uned.common.Utils;


public class MainServidor {

	public static void main(String[] args) throws Exception {
		Utils.setCodeBase(IServidor.class);
		
		Servidor servidor = new Servidor();
		IServidor remote = (IServidor)UnicastRemoteObject.exportObject(servidor, 8888);
		
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("Pepito", remote);
		
		System.out.println("Servidor listo, presione enter para terminar");
		System.in.read();
		
		registry.unbind("Pepito");
		UnicastRemoteObject.unexportObject(servidor, true);
		
		System.out.println("Servidor Terminado");
	}
}
