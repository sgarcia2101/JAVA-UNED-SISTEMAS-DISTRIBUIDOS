package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioAutenticacionInterface extends Remote {

  public int registrarCliente(String nombre) throws RemoteException;

  public int registrarRepositorio(String nombre) throws RemoteException;

  public boolean autenticarCliente(int id) throws RemoteException;

  public boolean autenticarRepositorio(int id) throws RemoteException;

}
