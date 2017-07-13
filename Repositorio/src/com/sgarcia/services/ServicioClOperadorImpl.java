package com.sgarcia.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioClOperadorInterface;

public class ServicioClOperadorImpl extends UnicastRemoteObject
    implements ServicioClOperadorInterface {

  private static final long serialVersionUID = -6039214596140705245L;

  private int servidorId;

  public ServicioClOperadorImpl(int servidorId) throws RemoteException {
    super();
    this.servidorId = servidorId;
  }

  public int getServidorId() {
    return servidorId;
  }

  public void setServidorId(int servidorId) {
    this.servidorId = servidorId;
  }

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
