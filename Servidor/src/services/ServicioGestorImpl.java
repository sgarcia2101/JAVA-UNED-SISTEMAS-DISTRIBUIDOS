package services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioGestorInterface;

public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {

  private static final long serialVersionUID = -6996834756184252642L;

  public ServicioGestorImpl() throws RemoteException, NotBoundException {
    super();
  }

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
