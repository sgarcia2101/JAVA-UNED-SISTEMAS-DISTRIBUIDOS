package com.sgarcia;

import java.io.File;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.gui.Gui;
import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.services.ServicioDatosInterface;
import com.sgarcia.commons.utils.FileUtils;
import com.sgarcia.commons.utils.RMIUtils;
import com.sgarcia.services.ServicioClOperadorImpl;
import com.sgarcia.services.ServicioSrOperadorImpl;

public class Repositorio {

  private static int sessionId = -1;

  public static void main(String[] args)
      throws RemoteException, NotBoundException, UnknownHostException, MalformedURLException {
    menuInicial();
  }

  public static void menuInicial()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {

    int option = 0;

    do {
      option = Gui
          .menu(new String[] {"Registrar un nuevo repositorio", "Autenticar Repositorio", "Salir"});

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
          System.exit(0);
          break;
      }
    } while (option >= 1 && option <= 3);
  }

  private static void registrar() throws RemoteException {
    String text = Gui.textInput("Introduzca el nombre de repositorio: ");

    if (text != null && !text.isEmpty()) {

      ServicioAutenticacionInterface servicioAutenticacion =
          ((ServicioAutenticacionInterface) RMIUtils
              .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));

      int id = servicioAutenticacion.registrarRepositorio(text);

      System.out.println("Repositorio registrado con identificador: " + id);
    }

  }

  private static boolean autenticar()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {
    int id = Gui.numberInput("Introduzca el identificador de repositorio: ");

    // Registro de servicio cliente-operador
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + id,
        new ServicioClOperadorImpl(id));

    // Registro de servicio servidor-operador
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + id,
        new ServicioSrOperadorImpl(id));

    boolean authenticated = false;
    try {
      ServicioAutenticacionInterface servicioAutenticacion =
          ((ServicioAutenticacionInterface) RMIUtils
              .getServiceByName(Constants.NOMBRE_SERVICIO_AUTENTICACION));

      authenticated = servicioAutenticacion.autenticarRepositorio(id);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    if (!authenticated) {
      System.out.println("El repositorio no ha podido ser autenticado.");
      RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + id);
      RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + id);
    } else {
      System.out.println("El repositorio ha sido autenticado correctamente. Id: " + id);
      sessionId = id;

      crearCarpetaRepositorio(id);
    }

    return authenticated;

  }

  private static void crearCarpetaRepositorio(int repositorioId) throws RemoteException {
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(repositorioId);
    File carpeta = new File(carpetaDir);
    if (!carpeta.exists()) {
      carpeta.mkdir();
    }
  }

  public static void menuPrincipal()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {

    int option = 0;

    do {
      option = com.sgarcia.commons.gui.Gui
          .menu(new String[] {"Listar clientes", "Listar ficheros del Cliente", "Salir"});

      switch (option) {
        case 1:
          listarClientes();
          break;
        case 2:
          listarFicherosCliente();
          break;
        case 3:
          RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + sessionId);
          RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + sessionId);
          sessionId = -1;
          option = 0;
          break;
      }
    } while (option >= 1 && option <= 3);

  }

  private static void listarClientes() throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    List<Cliente> clientes = servicioDatos.getClientesByRepositorioId(sessionId);

    System.out.println("          Listado de Clientes           ");
    System.out.println("----------------------------------------");
    for (Cliente cliente : clientes) {
      System.out.println(String.format("%3s", cliente.getId()) + " | " + cliente.getNombre());
    }
  }

  private static void listarFicherosCliente() {
    int clienteId = Gui.numberInput("Introduzca el identificador de usuario: ");
    
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(sessionId) + File.separator
        + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    List<File> ficheros = FileUtils.listarFicherosCarpeta(carpetaDir);

    List<String> listaFicheros = new ArrayList<String>();
    for (File fichero : ficheros) {
      listaFicheros.add(fichero.getName());
    }
    
    System.out.println();
    System.out.println("        Listar Ficheros Cliente         ");
    System.out.println("----------------------------------------");
    for (String fichero : listaFicheros) {
      System.out.println(fichero);
    }
  }
}
