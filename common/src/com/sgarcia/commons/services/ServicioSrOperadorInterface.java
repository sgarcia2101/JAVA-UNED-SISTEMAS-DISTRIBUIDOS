package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * The Interface ServicioSrOperadorInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioSrOperadorInterface extends Remote {

  /**
   * Crear carpeta cliente.
   *
   * @param clienteId the cliente id
   * @throws RemoteException the remote exception
   */
  public void crearCarpetaCliente(int clienteId) throws RemoteException;

  /**
   * Listar ficheros cliente.
   *
   * @param clienteId the cliente id
   * @return the list
   * @throws RemoteException the remote exception
   */
  public List<String> listarFicherosCliente(int clienteId) throws RemoteException;

  /**
   * Bajar fichero cliente.
   *
   * @param nombreServicio the nombre servicio
   * @param clienteId the cliente id
   * @param nombreFichero the nombre fichero
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean bajarFicheroCliente(String nombreServicio, int clienteId, String nombreFichero)
      throws RemoteException;

}
