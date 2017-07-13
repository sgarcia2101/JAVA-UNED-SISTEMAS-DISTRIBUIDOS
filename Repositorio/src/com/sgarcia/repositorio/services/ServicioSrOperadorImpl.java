package com.sgarcia.repositorio.services;

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

/**
 * The Class ServicioSrOperadorImpl.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class ServicioSrOperadorImpl extends UnicastRemoteObject
    implements ServicioSrOperadorInterface {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6755678901120434629L;

  /** The servidor id. */
  private int servidorId;

  /**
   * Instantiates a new servicio sr operador impl.
   *
   * @param servidorId the servidor id
   * @throws RemoteException the remote exception
   */
  public ServicioSrOperadorImpl(int servidorId) throws RemoteException {
    super();
    this.servidorId = servidorId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioSrOperadorInterface#crearCarpetaCliente(int)
   */
  @Override
  public void crearCarpetaCliente(int clienteId) throws RemoteException {
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId)
        + File.separator + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    FileUtils.crearCarpeta(carpetaDir);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioSrOperadorInterface#listarFicherosCliente(int)
   */
  @Override
  public List<String> listarFicherosCliente(int clienteId) throws RemoteException {
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId)
        + File.separator + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    List<File> ficheros = FileUtils.listarFicherosCarpeta(carpetaDir);

    List<String> listaFicheros = new ArrayList<String>();
    for (File fichero : ficheros) {
      listaFicheros.add(fichero.getName());
    }
    return listaFicheros;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sgarcia.commons.services.ServicioSrOperadorInterface#bajarFicheroCliente(java.lang.String,
   * int, java.lang.String)
   */
  @Override
  public boolean bajarFicheroCliente(String nombreServicio, int clienteId, String nombreFichero)
      throws RemoteException {
    ServicioDiscoClienteInterface servicioDiscoCliente =
        ((ServicioDiscoClienteInterface) RMIUtils.getServiceByName(nombreServicio));

    String ficheroDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId)
        + File.separator + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);

    Fichero fichero = new Fichero(ficheroDir, nombreFichero, String.valueOf(clienteId));

    boolean downloaded = servicioDiscoCliente.bajarFichero(fichero);

    return downloaded;
  }

}
