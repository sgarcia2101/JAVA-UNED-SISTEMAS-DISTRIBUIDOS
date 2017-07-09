package com.sgarcia;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.sgarcia.commons.gui.Gui;
import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.utils.Utils;
import com.sgarcia.services.ServicioDiscoClienteImpl;

/**
 * @author sergio
 *
 */
public class Cliente {

  private static ServicioAutenticacionInterface servicioAutenticacion;

  private static int sessionId = -1;

  public static void main(String[] args)
      throws RemoteException, NotBoundException, UnknownHostException {

    Registry registry = LocateRegistry.getRegistry();
    servicioAutenticacion = (ServicioAutenticacionInterface) registry
        .lookup("rmi://127.0.0.1:9000/servicioAutenticacion");

    menuInicial();
  }

  public static void menuInicial() throws RemoteException, UnknownHostException, NotBoundException {

    int option = 0;

    do {
      option = Gui.menu(new String[] {"Registrar un nuevo usuario",
          "Autenticarse en el sistema (hacer login)", "Salir"});

      switch (option) {
        case 1:
          registrar();
          break;
        case 2:
          autenticar();
          break;
        case 3:
          System.exit(0);
          break;
      }
    } while (option >= 1 || option <= 3);
  }

  private static void registrar() throws RemoteException {
    String text = Gui.textInput("Introduzca el nombre de usuario:");

    if (text != null && !text.isEmpty()) {

      int sessionId = servicioAutenticacion.registrar(text);

      System.out.println("Usuario registrado con identificador: " + sessionId);
    }

  }

  private static void autenticar() throws RemoteException, UnknownHostException, NotBoundException {
    int id = Gui.numberInput("Introduzca el identificador de usuario: ");

    Registry registry = LocateRegistry.getRegistry();

    // Registro de servicio de disco del cliente
    ServicioDiscoClienteImpl servicioDiscoCliente = new ServicioDiscoClienteImpl();
    Utils.registryService(registry, "servicioDiscoCliente/" + id, 9001, servicioDiscoCliente);

    boolean authenticated = false;
    try{
      authenticated = servicioAutenticacion.autenticar(id);
    } catch(RuntimeException e){
      System.out.print(e.getMessage() + " ");
    }

    if (!authenticated) {
      System.out.println("El usuario no ha podido ser autenticado.");
      Utils.unregistryService(registry, "servicioDiscoCliente/" + id, 9001, servicioDiscoCliente);
    } else {
      sessionId = id;
      System.out.println("El usuario ha sido autenticado correctamente. Id: " + sessionId);
    }

  }

  public void menuPrincipal() throws RemoteException, UnknownHostException, NotBoundException {

    int option = 0;

    do {
      option = com.sgarcia.commons.gui.Gui
          .menu(new String[] {"Subir fichero", "Bajar fichero", "Borrar fichero",
              "Compartir fichero", "Listar ficheros", "Listar Clientes del sistema", "Salir"});

      switch (option) {
        case 1:
          subirFichero();
          break;
        case 2:
          bajarFichero();
          break;
        case 3:
          borrarFichero();
          break;
        case 4:
          compartirFichero();
          break;
        case 5:
          listarFicheros();
          break;
        case 6:
          listarClientes();
          break;
        case 7:
          menuInicial();
          break;
      }
    } while (option < 1 || option > 7);


  }

  private void subirFichero() {
    // TODO Auto-generated method stub

  }

  private void bajarFichero() {
    // TODO Auto-generated method stub

  }

  private void borrarFichero() {
    // TODO Auto-generated method stub

  }

  private void compartirFichero() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private void listarFicheros() {
    // TODO Auto-generated method stub

  }

  private void listarClientes() {
    // TODO Auto-generated method stub

  }

}
