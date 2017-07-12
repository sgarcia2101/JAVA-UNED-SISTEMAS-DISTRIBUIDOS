package com.sgarcia;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.gui.Gui;
import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.utils.RMIUtils;
import com.sgarcia.services.ServicioDiscoClienteImpl;

/**
 * @author sergio
 *
 */
public class Cliente {

  // private static ServicioAutenticacionInterface servicioAutenticacion;
  //
  // private static ServicioGestorInterface servicioGestor;

  private static int sessionId = -1;

  public static void main(String[] args)
      throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {

    // ServicioAutenticacionInterface servicioAutenticacion = ((ServicioAutenticacionInterface)
    // RMIUtils
    // .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));
    //
    // servicioGestor =
    // ((ServicioGestorInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_GESTOR));

    menuInicial();
  }

  public static void menuInicial()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {

    int option = 0;

    do {
      option = Gui.menu(new String[] {"Registrar un nuevo usuario", "Autenticar Usuario", "Salir"});

      switch (option) {
        case 1:
          registrar();
          break;
        case 2:
          autenticar();
          break;
        case 3:
          cerrarSesion();
          break;
      }
    } while (option >= 1 && option <= 3);
  }

  private static void registrar() throws RemoteException {
    String text = Gui.textInput("Introduzca el nombre de usuario:");

    if (text != null && !text.isEmpty()) {

      ServicioAutenticacionInterface servicioAutenticacion =
          ((ServicioAutenticacionInterface) RMIUtils
              .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));

      int id = servicioAutenticacion.registrarCliente(text);

      System.out.println("Usuario registrado con identificador: " + id);
    }

  }

  private static void autenticar()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {
    int id = Gui.numberInput("Introduzca el identificador de usuario: ");

    // Registro de servicio de disco del cliente
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + id,
        new ServicioDiscoClienteImpl());

    boolean authenticated = false;
    try {
      ServicioAutenticacionInterface servicioAutenticacion =
          ((ServicioAutenticacionInterface) RMIUtils
              .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));

      authenticated = servicioAutenticacion.autenticarCliente(id);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    if (!authenticated) {
      System.out.println("El usuario no ha podido ser autenticado.");
      RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + id);
    } else {
      System.out.println("El usuario ha sido autenticado correctamente. Id: " + id);
      sessionId = id;
    }

  }

  private static void cerrarSesion()
      throws RemoteException, MalformedURLException, NotBoundException {
    if (sessionId != -1) {
      RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + sessionId);
    }
    System.exit(0);
  }

  public void menuPrincipal()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {

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
    } while (option >= 1 && option <= 7);


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
