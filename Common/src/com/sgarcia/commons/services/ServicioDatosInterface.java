package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.entities.Repositorio;

public interface ServicioDatosInterface extends Remote {

  public Cliente registrarCliente(String nombre) throws RemoteException;

  public int registrarRepositorio(String nombre) throws RemoteException;

  public Cliente getCliente(int id) throws RemoteException;

  public Repositorio getRepositorio(int id) throws RemoteException;
  
  public List<Cliente> getClientes() throws RemoteException;
  
  public List<Repositorio> getRepositorios() throws RemoteException;

  public Map<Repositorio, List<Cliente>> getRepositoriosClientes() throws RemoteException;

  public List<Cliente> getClientesByRepositorioId(int repositorioId) throws RemoteException;

}
