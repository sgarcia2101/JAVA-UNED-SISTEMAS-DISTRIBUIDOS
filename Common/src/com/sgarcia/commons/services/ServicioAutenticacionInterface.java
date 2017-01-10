package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioAutenticacionInterface extends Remote {

	public int registrar(String nombre) throws RemoteException;

	public boolean autenticar(int id) throws RemoteException;

}
