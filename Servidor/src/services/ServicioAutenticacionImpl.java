package services;

import java.rmi.RemoteException;

import com.sgarcia.commons.services.ServicioAutenticacionInterface;

/**
 * @author sergio
 *
 */
public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

	@Override
	public int registrar(String nombre) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean autenticar(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
