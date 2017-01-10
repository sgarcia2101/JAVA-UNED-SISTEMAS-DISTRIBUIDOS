package services;

import java.rmi.RemoteException;
import java.util.List;

import com.sgarcia.commons.Fichero;
import com.sgarcia.commons.services.ServicioGestorInterface;

public class ServicioGestorImpl implements ServicioGestorInterface {

	@Override
	public int subir(Fichero fichero) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Fichero bajar(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrar(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fichero> listar(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
