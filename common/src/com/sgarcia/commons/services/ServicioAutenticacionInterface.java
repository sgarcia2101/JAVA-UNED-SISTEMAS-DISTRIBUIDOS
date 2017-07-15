package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The Interface ServicioAutenticacionInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioAutenticacionInterface extends Remote {

  /**
   * Registrar cliente.
   *
   * @param nombre the nombre
   * @return the int
   * @throws RemoteException the remote exception
   */
  public int registrarCliente(String nombre) throws RemoteException;

  /**
   * Registrar repositorio.
   *
   * @param nombre the nombre
   * @return the int
   * @throws RemoteException the remote exception
   */
  public int registrarRepositorio(String nombre) throws RemoteException;

  /**
   * Autenticar cliente.
   *
   * @param id the id
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean autenticarCliente(int id) throws RemoteException;

  /**
   * Autenticar repositorio.
   *
   * @param id the id
   * @return true, if successful
   * @throws RemoteException the remote exception
   */
  public boolean autenticarRepositorio(int id) throws RemoteException;

}
