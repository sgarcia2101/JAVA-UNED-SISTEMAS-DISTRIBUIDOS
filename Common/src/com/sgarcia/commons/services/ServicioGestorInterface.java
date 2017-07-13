package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.sgarcia.commons.entities.Fichero;

public interface ServicioGestorInterface extends Remote {

  public String subir(Fichero fichero) throws RemoteException;

  public boolean bajar(int clienteId, String name) throws RemoteException;

  public String borrar(Fichero fichero) throws RemoteException;

  public List<String> listar(int id) throws RemoteException;

}
