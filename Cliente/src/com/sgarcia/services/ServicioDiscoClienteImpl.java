package com.sgarcia.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioDiscoClienteInterface;

public class ServicioDiscoClienteImpl extends UnicastRemoteObject
    implements ServicioDiscoClienteInterface {

  private static final long serialVersionUID = -4714224212802634239L;

  private int clienteId;

  public ServicioDiscoClienteImpl(int id) throws RemoteException {
    super();
    this.clienteId = id;
  }

  @Override
  public boolean bajarFichero(Fichero fichero) throws RemoteException {
    String carpetaDir = Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);

    OutputStream os;
    try {
      os = new FileOutputStream(carpetaDir + File.separator + fichero.obtenerNombre());
    } catch (FileNotFoundException e) {
      return false;
    }

    boolean uploaded = fichero.escribirEn(os);

    return uploaded;
  }

}
