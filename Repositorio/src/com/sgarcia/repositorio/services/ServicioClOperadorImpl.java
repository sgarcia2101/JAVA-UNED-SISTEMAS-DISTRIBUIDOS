package com.sgarcia.repositorio.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioClOperadorInterface;

/**
 * The Class ServicioClOperadorImpl.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class ServicioClOperadorImpl extends UnicastRemoteObject
    implements ServicioClOperadorInterface {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6039214596140705245L;

  /** The servidor id. */
  private int servidorId;

  /**
   * Instantiates a new servicio cl operador impl.
   *
   * @param servidorId the servidor id
   * @throws RemoteException the remote exception
   */
  public ServicioClOperadorImpl(int servidorId) throws RemoteException {
    super();
    this.servidorId = servidorId;
  }

  /**
   * Gets the servidor id.
   *
   * @return the servidor id
   */
  public int getServidorId() {
    return servidorId;
  }

  /**
   * Sets the servidor id.
   *
   * @param servidorId the new servidor id
   */
  public void setServidorId(int servidorId) {
    this.servidorId = servidorId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sgarcia.commons.services.ServicioClOperadorInterface#subir(com.sgarcia.commons.entities.
   * Fichero)
   */
  @Override
  public boolean subir(Fichero fichero) throws RemoteException {
    int clienteId = Integer.valueOf(fichero.obtenerPropietario());

    String carpetaDestino = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId)
        + File.separator + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);

    OutputStream os;
    try {
      os = new FileOutputStream(carpetaDestino + File.separator + fichero.obtenerNombre());
    } catch (FileNotFoundException e) {
      return false;
    }

    boolean uploaded = false;
    try {
      uploaded = fichero.escribirEn(os);
    } catch (Exception e) {
      return false;
    }

    return uploaded;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.sgarcia.commons.services.ServicioClOperadorInterface#borrar(com.sgarcia.commons.entities.
   * Fichero)
   */
  @Override
  public boolean borrar(Fichero fichero) throws RemoteException {
    int clienteId = Integer.valueOf(fichero.obtenerPropietario());

    String carpetaFichero = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId)
        + File.separator + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);

    File file = new File(carpetaFichero + File.separator + fichero.obtenerNombre());

    boolean deleted = false;
    try {
      deleted = file.delete();
    } catch (Exception e) {
      return false;
    }

    return deleted;
  }

}
