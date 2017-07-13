package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.sgarcia.commons.entities.Fichero;

/**
 * The Interface ServicioGestorInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioGestorInterface extends Remote {

  /**
   * Subir.
   *
   * @param fichero the fichero
   * @return the string
   * @throws RemoteException the remote exception
   */
  public String subir(Fichero fichero) throws RemoteException;

  /**
   * Bajar.
   *
   * @param clienteId the cliente id
   * @param name the name
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean bajar(int clienteId, String name) throws RemoteException;

  /**
   * Borrar.
   *
   * @param fichero the fichero
   * @return the string
   * @throws RemoteException the remote exception
   */
  public String borrar(Fichero fichero) throws RemoteException;

  /**
   * Listar.
   *
   * @param id the id
   * @return the list
   * @throws RemoteException the remote exception
   */
  public List<String> listar(int id) throws RemoteException;

}
