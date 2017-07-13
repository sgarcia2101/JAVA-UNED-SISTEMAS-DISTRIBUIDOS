package com.sgarcia.services;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioDiscoClienteInterface;
import com.sgarcia.commons.services.ServicioSrOperadorInterface;
import com.sgarcia.commons.utils.FileUtils;
import com.sgarcia.commons.utils.RMIUtils;

public class ServicioSrOperadorImpl extends UnicastRemoteObject
    implements ServicioSrOperadorInterface {

  private static final long serialVersionUID = 6755678901120434629L;

  private int servidorId;

  public ServicioSrOperadorImpl(int servidorId) throws RemoteException {
    super();
    this.servidorId = servidorId;
  }

  @Override
  public void crearCarpetaCliente(int clienteId) throws RemoteException {
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId) + File.separator
        + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    FileUtils.crearCarpeta(carpetaDir);
  }

  @Override
  public List<String> listarFicherosCliente(int clienteId) throws RemoteException {
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId) + File.separator
        + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    List<File> ficheros = FileUtils.listarFicherosCarpeta(carpetaDir);

    List<String> listaFicheros = new ArrayList<String>();
    for (File fichero : ficheros) {
      listaFicheros.add(fichero.getName());
    }
    return listaFicheros;
  }

  @Override
  public boolean bajarFicheroCliente(String nombreServicio, int clienteId, String nombreFichero)
      throws RemoteException {
    ServicioDiscoClienteInterface servicioDiscoCliente =
        ((ServicioDiscoClienteInterface) RMIUtils.getServiceByName(nombreServicio));

    String ficheroDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId) + File.separator
        + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);

    Fichero fichero = new Fichero(ficheroDir, nombreFichero, String.valueOf(clienteId));

    boolean downloaded = servicioDiscoCliente.bajarFichero(fichero);

    return downloaded;
  }

}
