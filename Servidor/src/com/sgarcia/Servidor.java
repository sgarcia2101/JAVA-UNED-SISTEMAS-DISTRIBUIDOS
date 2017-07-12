package com.sgarcia;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.entities.Repositorio;
import com.sgarcia.commons.gui.Gui;
import com.sgarcia.commons.services.ServicioDatosInterface;
import com.sgarcia.commons.utils.RMIUtils;

import services.ServicioAutenticacionImpl;
import services.ServicioDatosImpl;
import services.ServicioGestorImpl;

/**
 * @author sergio
 *
 */
public class Servidor {

  public static void main(String[] args) throws NotBoundException, IOException {

    RMIUtils.startRegistry();

    // Registro de servicio de autenticacion
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_AUTENTICACION,
        new ServicioAutenticacionImpl());

    // Registro de servicio gestor
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_GESTOR, new ServicioGestorImpl());

    // Registro de servicio de datos
    RMIUtils.registryService(Constants.NOMBRE_SERVICIO_DATOS, new ServicioDatosImpl());

    menuInicial();
  }

  public static void menuInicial()
      throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {

    int option = 0;

    do {
      option = Gui.menu(new String[] {"Listar Clientes", "Listar Repositorios",
          "Listar Parejas Repositorio-Cliente", "Salir"});

      switch (option) {
        case 1:
          listarClientes();
          break;
        case 2:
          listarRepositorios();
          break;
        case 3:
          listarParejasRepositorioCliente();
          break;
        case 4:
          salir();
          break;
      }
    } while (option >= 1 && option <= 4);
  }

  private static void listarClientes() throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    List<Cliente> clientes = servicioDatos.getClientes();

    System.out.println("          Listado de Clientes           ");
    System.out.println("----------------------------------------");
    for (Cliente cliente : clientes) {
      System.out.println(String.format("%3s", cliente.getId()) + " | " + cliente.getNombre());
    }
  }

  private static void listarRepositorios() throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    List<Repositorio> repositorios = servicioDatos.getRepositorios();

    System.out.println("        Listado de Repositorios         ");
    System.out.println("----------------------------------------");
    for (Repositorio repositorio : repositorios) {
      System.out
          .println(String.format("%3s", repositorio.getId()) + " | " + repositorio.getNombre());
    }
  }

  private static void listarParejasRepositorioCliente() throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Map<Repositorio, List<Cliente>> repositoriosClientes = servicioDatos.getRepositoriosClientes();

    System.out.println(" Listado de Parejas Repositorio-Cliente ");
    System.out.println("----------------------------------------");
    for (Entry<Repositorio, List<Cliente>> repositorioClientesEntry : repositoriosClientes
        .entrySet()) {
      Repositorio repositorio = repositorioClientesEntry.getKey();
      for (Cliente cliente : repositorioClientesEntry.getValue()) {
        System.out
            .print(String.format("%3s", repositorio.getId()) + " | " + repositorio.getNombre());
        System.out.print("  <-->  ");
        System.out.println(String.format("%3s", cliente.getId()) + " | " + cliente.getNombre());
      }
    }
  }

  private static void salir() throws RemoteException, MalformedURLException, NotBoundException {
    RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_AUTENTICACION);
    RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_GESTOR);
    RMIUtils.unregistryService(Constants.NOMBRE_SERVICIO_DATOS);
    System.exit(0);
  }

}
