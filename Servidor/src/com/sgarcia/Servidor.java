package com.sgarcia;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;

import com.sgarcia.commons.utils.Utils;

import services.ServicioAutenticacionImpl;
import services.ServicioDatosImpl;
import services.ServicioGestorImpl;

/**
 * @author sergio
 *
 */
public class Servidor {

  public static void main(String[] args) throws NotBoundException, IOException {

    Registry registry = Utils.createRegistry();

    // Registro de servicio de autenticacion
    ServicioAutenticacionImpl servicioAutenticacion = new ServicioAutenticacionImpl();
    Utils.registryService(registry, "servicioAutenticacion", 9000, servicioAutenticacion);

    // Registro de servicio gestor
    ServicioGestorImpl servicioGestor = new ServicioGestorImpl();
    Utils.registryService(registry, "servicioGestor", 9000, servicioGestor);

    // Registro de servicio de datos
    ServicioDatosImpl servicioDatos = new ServicioDatosImpl();
    Utils.registryService(registry, "servicioDatos", 9000, servicioDatos);

    System.out.println("Servidor listo, presione enter para terminar");
    System.in.read();

    Utils.unregistryService(registry, "servicioAutenticacion", 9000, servicioAutenticacion);

    Utils.unregistryService(registry, "servicioGestor", 9000, servicioGestor);

    Utils.unregistryService(registry, "servicioDatos", 9000, servicioDatos);

    System.out.println("Servidor Terminado");
  }

}
