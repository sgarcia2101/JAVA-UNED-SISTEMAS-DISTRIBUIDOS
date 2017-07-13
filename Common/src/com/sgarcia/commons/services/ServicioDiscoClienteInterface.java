package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sgarcia.commons.entities.Fichero;

public interface ServicioDiscoClienteInterface extends Remote {

  boolean bajarFichero(Fichero fichero) throws RemoteException;

}
