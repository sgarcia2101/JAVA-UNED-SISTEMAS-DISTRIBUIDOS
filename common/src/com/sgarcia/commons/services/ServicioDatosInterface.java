package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.entities.Repositorio;

/**
 * The Interface ServicioDatosInterface.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public interface ServicioDatosInterface extends Remote {

  /**
   * Registrar cliente.
   *
   * @param nombre the nombre
   * @return the cliente
   * @throws RemoteException the remote exception
   */
  public Cliente registrarCliente(String nombre) throws RemoteException;

  /**
   * Registrar repositorio.
   *
   * @param nombre the nombre
   * @return the int
   * @throws RemoteException the remote exception
   */
  public int registrarRepositorio(String nombre) throws RemoteException;

  /**
   * Gets the cliente.
   *
   * @param id the id
   * @return the cliente
   * @throws RemoteException the remote exception
   */
  public Cliente getCliente(int id) throws RemoteException;

  /**
   * Gets the repositorio.
   *
   * @param id the id
   * @return the repositorio
   * @throws RemoteException the remote exception
   */
  public Repositorio getRepositorio(int id) throws RemoteException;

  /**
   * Gets the clientes.
   *
   * @return the clientes
   * @throws RemoteException the remote exception
   */
  public List<Cliente> getClientes() throws RemoteException;

  /**
   * Gets the repositorios.
   *
   * @return the repositorios
   * @throws RemoteException the remote exception
   */
  public List<Repositorio> getRepositorios() throws RemoteException;

  /**
   * Gets the repositorios clientes.
   *
   * @return the repositorios clientes
   * @throws RemoteException the remote exception
   */
  public Map<Repositorio, List<Cliente>> getRepositoriosClientes() throws RemoteException;

  /**
   * Gets the clientes by repositorio id.
   *
   * @param repositorioId the repositorio id
   * @return the clientes by repositorio id
   * @throws RemoteException the remote exception
   */
  public List<Cliente> getClientesByRepositorioId(int repositorioId) throws RemoteException;

}
