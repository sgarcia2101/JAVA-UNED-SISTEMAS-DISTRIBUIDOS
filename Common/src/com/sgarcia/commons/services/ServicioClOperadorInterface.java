package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sgarcia.commons.entities.Fichero;

public interface ServicioClOperadorInterface extends Remote {
  
  public boolean subir(Fichero fichero) throws RemoteException;

  public boolean borrar(Fichero fichero) throws RemoteException;
  
}
