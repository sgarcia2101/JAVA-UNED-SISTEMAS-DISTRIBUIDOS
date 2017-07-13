package com.sgarcia;

import java.io.File;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.gui.Gui;
import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.services.ServicioClOperadorInterface;
import com.sgarcia.commons.services.ServicioDatosInterface;
import com.sgarcia.commons.services.ServicioGestorInterface;
import com.sgarcia.commons.utils.RMIUtils;
import com.sgarcia.services.ServicioDiscoClienteImpl;

/**
 * @author sergio
 *
 */
public class Cliente {

  private static int sessionId = -1;

  public static void main(String[] args)
      throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
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
          boolean authenticated = autenticar();
          if (authenticated) {
            menuPrincipal();
          }
          break;
        case 3:
          cerrarSesion();
          break;
      }
    } while (option >= 1 && option <= 3);
  }

  private static void registrar() throws RemoteException {
    String text = Gui.textInput("Introduzca el nombre de usuario: ");

    if (text != null && !text.isEmpty()) {

      ServicioAutenticacionInterface servicioAutenticacion =
          ((ServicioAutenticacionInterface) RMIUtils
              .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));

      int id = servicioAutenticacion.registrarCliente(text);

      System.out.println("Usuario registrado con identificador: " + id);
    }

  }

  private static boolean autenticar()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {
    int id = Gui.numberInput("Introduzca el identificador de usuario: ");

    // Registro de servicio de disco del cliente
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + id,
        new ServicioDiscoClienteImpl(id));

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

      crearCarpetaCliente(id);
    }

    return authenticated;

  }

  private static void crearCarpetaCliente(int id) {
    String carpetaDir = Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(id);
    File carpeta = new File(carpetaDir);
    if (!carpeta.exists()) {
      carpeta.mkdir();
    }
  }

  private static void cerrarSesion()
      throws RemoteException, MalformedURLException, NotBoundException {
    if (sessionId != -1) {
      RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + sessionId);
    }
    System.exit(0);
  }

  public static void menuPrincipal()
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

  private static void subirFichero() throws RemoteException {
    String nombreFichero = Gui.textInput("Introduzca el nombre del fichero: ");

    ServicioGestorInterface servicioGestor =
        ((ServicioGestorInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_GESTOR));

    Fichero fichero = null;
    if (nombreFichero.split(File.separator).length > 1) {
      String path = nombreFichero.substring(0, nombreFichero.lastIndexOf(File.separator));
      String fileName = nombreFichero.substring(nombreFichero.lastIndexOf(File.separator) + 1, nombreFichero.length());
      fichero = new Fichero(path, fileName, String.valueOf(sessionId));
    } else {
      fichero = new Fichero(nombreFichero, String.valueOf(sessionId));
    }

    String serviceName = servicioGestor.subir(fichero);

    ServicioClOperadorInterface servicioClOperador =
        ((ServicioClOperadorInterface) RMIUtils.getServiceByName(serviceName));

    boolean uploaded = servicioClOperador.subir(fichero);

    if (uploaded) {
      System.out.println("El fichero se ha subido correctamente.");
    } else {
      System.out.println("No ha sido posible subir el fichero.");
    }
  }

  private static void bajarFichero() throws RemoteException {
    String nombreFichero = Gui.textInput("Introduzca el nombre del fichero: ");

    ServicioGestorInterface servicioGestor =
        ((ServicioGestorInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_GESTOR));

    boolean downloaded = servicioGestor.bajar(sessionId, nombreFichero);

    if (downloaded) {
      System.out.println("El fichero se ha descargado correctamente.");
    } else {
      System.out.println("No ha sido posible descargar el fichero.");
    }
  }

  private static void borrarFichero() throws RemoteException {
    String nombreFichero = Gui.textInput("Introduzca el nombre del fichero: ");

    ServicioGestorInterface servicioGestor =
        ((ServicioGestorInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_GESTOR));

    Fichero fichero = new Fichero(nombreFichero, String.valueOf(sessionId));

    String serviceName = servicioGestor.borrar(fichero);

    ServicioClOperadorInterface servicioClOperador =
        ((ServicioClOperadorInterface) RMIUtils.getServiceByName(serviceName));

    boolean deleted = servicioClOperador.borrar(fichero);

    if (deleted) {
      System.out.println("El fichero se ha borrado correctamente.");
    } else {
      System.out.println("No ha sido posible borrar el fichero.");
    }

  }

  private static void compartirFichero() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private static void listarFicheros() throws RemoteException {
    ServicioGestorInterface servicioGestor =
        ((ServicioGestorInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_GESTOR));

    List<String> listaFicheros = servicioGestor.listar(sessionId);

    System.out.println("          Listado de Ficheros           ");
    System.out.println("----------------------------------------");
    for (String fichero : listaFicheros) {
      System.out.println(fichero);
    }
  }

  private static void listarClientes() throws RemoteException {

    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    List<com.sgarcia.commons.entities.Cliente> clientes = servicioDatos.getClientes();

    System.out.println("          Listado de Clientes           ");
    System.out.println("----------------------------------------");
    for (com.sgarcia.commons.entities.Cliente cliente : clientes) {
      System.out.println(String.format("%3s", cliente.getId()) + " | " + cliente.getNombre());
    }
  }

}
