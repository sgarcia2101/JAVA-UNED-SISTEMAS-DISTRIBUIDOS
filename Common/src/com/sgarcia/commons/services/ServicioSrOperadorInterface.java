package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioSrOperadorInterface extends Remote {

  public void crearCarpetaCliente(int clienteId) throws RemoteException;

}
