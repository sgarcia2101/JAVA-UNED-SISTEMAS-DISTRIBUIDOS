package com.sgarcia.commons.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.sgarcia.commons.Fichero;

public interface ServicioGestorInterface extends Remote {

	public int subir(Fichero fichero) throws RemoteException;
	
	public Fichero bajar(int id) throws RemoteException;
	
	public boolean borrar(int id) throws RemoteException;
	
	public List<Fichero> listar(int id) throws RemoteException;

}
