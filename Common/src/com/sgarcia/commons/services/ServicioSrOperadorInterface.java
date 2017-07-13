package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioSrOperadorInterface extends Remote {

  public void crearCarpetaCliente(int clienteId) throws RemoteException;

  public List<String> listarFicherosCliente(int clienteId) throws RemoteException;

  public boolean bajarFicheroCliente(String nombreServicio, int clienteId, String nombreFichero)
      throws RemoteException;

}
