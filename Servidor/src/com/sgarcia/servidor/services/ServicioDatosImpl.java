package com.sgarcia.servidor.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.entities.Repositorio;
import com.sgarcia.commons.services.ServicioDatosInterface;

/**
 * The Class ServicioDatosImpl.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class ServicioDatosImpl extends UnicastRemoteObject implements ServicioDatosInterface {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 5211348462046004324L;

  /** The registro clientes. */
  private Map<Integer, Cliente> registroClientes = new HashMap<Integer, Cliente>();

  /** The registro repositorios. */
  private Map<Integer, Repositorio> registroRepositorios = new HashMap<Integer, Repositorio>();

  /** The relacion cliente repositorio. */
  private Map<Integer, Integer> relacionClienteRepositorio = new HashMap<Integer, Integer>();

  /** The relacion repositorio clientes. */
  private Map<Integer, List<Integer>> relacionRepositorioClientes =
      new HashMap<Integer, List<Integer>>();

  /** The usuarios cont. */
  private int usuariosCont = 0;

  /** The repositorios cont. */
  private int repositoriosCont = 0;

  /**
   * Instantiates a new servicio datos impl.
   *
   * @throws RemoteException the remote exception
   */
  public ServicioDatosImpl() throws RemoteException {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#registrarCliente(java.lang.String)
   */
  @Override
  public Cliente registrarCliente(String nombre) throws RemoteException {
    int id = ++usuariosCont;

    int random = new Random().nextInt(registroRepositorios.keySet().size());
    Integer repositorioRandom = (Integer) registroRepositorios.keySet().toArray()[random];

    Cliente usuario = new Cliente(id, nombre, repositorioRandom);
    registroClientes.put(id, usuario);

    relacionClienteRepositorio.put(usuario.getId(), repositorioRandom);
    if (relacionRepositorioClientes.get(repositorioRandom) == null) {
      relacionRepositorioClientes.put(repositorioRandom, new ArrayList<Integer>());
    }
    relacionRepositorioClientes.get(repositorioRandom).add(usuario.getId());

    return usuario;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#registrarRepositorio(java.lang.String)
   */
  @Override
  public int registrarRepositorio(String nombre) throws RemoteException {
    int id = ++repositoriosCont;
    Repositorio repositorio = new Repositorio(id, nombre);
    registroRepositorios.put(id, repositorio);
    return id;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getCliente(int)
   */
  @Override
  public Cliente getCliente(int id) throws RemoteException {
    return registroClientes.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getRepositorio(int)
   */
  @Override
  public Repositorio getRepositorio(int id) throws RemoteException {
    return registroRepositorios.get(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getClientes()
   */
  @Override
  public List<Cliente> getClientes() throws RemoteException {
    return new ArrayList<Cliente>(registroClientes.values());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getRepositorios()
   */
  @Override
  public List<Repositorio> getRepositorios() throws RemoteException {
    return new ArrayList<Repositorio>(registroRepositorios.values());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getRepositoriosClientes()
   */
  @Override
  public Map<Repositorio, List<Cliente>> getRepositoriosClientes() throws RemoteException {
    Map<Repositorio, List<Cliente>> repositoriosClientes =
        new HashMap<Repositorio, List<Cliente>>();
    for (Entry<Integer, List<Integer>> repositorioClientesEntry : relacionRepositorioClientes
        .entrySet()) {
      Repositorio repositorio = registroRepositorios.get(repositorioClientesEntry.getKey());
      repositoriosClientes.put(repositorio, new ArrayList<Cliente>());
      for (Integer clienteId : repositorioClientesEntry.getValue()) {
        Cliente cliente = registroClientes.get(clienteId);
        repositoriosClientes.get(repositorio).add(cliente);
      }
    }
    return repositoriosClientes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioDatosInterface#getClientesByRepositorioId(int)
   */
  @Override
  public List<Cliente> getClientesByRepositorioId(int repositorioId) throws RemoteException {
    List<Cliente> clientes = new ArrayList<Cliente>();

    List<Integer> clientesId = relacionRepositorioClientes.get(Integer.valueOf(repositorioId));

    if (clientesId == null) {
      return clientes;
    }

    for (Integer clienteId : clientesId) {
      clientes.add(registroClientes.get(clienteId));
    }

    return clientes;
  }



}
