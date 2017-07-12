package com.sgarcia.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.services.ServicioSrOperadorInterface;
import com.sgarcia.commons.utils.FileUtils;

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
    String carpetaDir = Constants.CARPETA_REPOSITORIO_PREFIX + String.valueOf(this.servidorId) + "/"
        + Constants.CARPETA_CLIENTE_PREFIX + String.valueOf(clienteId);
    FileUtils.crearCarpeta(carpetaDir);
  }

}
