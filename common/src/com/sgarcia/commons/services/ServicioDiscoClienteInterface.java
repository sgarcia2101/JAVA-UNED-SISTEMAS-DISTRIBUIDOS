package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sgarcia.commons.entities.Fichero;

/**
 * The Interface ServicioDiscoClienteInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioDiscoClienteInterface extends Remote {

  /**
   * Bajar fichero.
   *
   * @param fichero the fichero
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  boolean bajarFichero(Fichero fichero) throws RemoteException;

}
