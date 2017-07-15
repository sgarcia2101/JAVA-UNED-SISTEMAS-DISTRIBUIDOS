package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sgarcia.commons.entities.Fichero;

/**
 * The Interface ServicioClOperadorInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioClOperadorInterface extends Remote {

  /**
   * Subir.
   *
   * @param fichero the fichero
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean subir(Fichero fichero) throws RemoteException;

  /**
   * Borrar.
   *
   * @param fichero the fichero
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean borrar(Fichero fichero) throws RemoteException;

}
