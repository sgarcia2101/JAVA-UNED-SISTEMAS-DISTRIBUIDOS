package com.sgarcia.servidor.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.entities.Fichero;
import com.sgarcia.commons.services.ServicioDatosInterface;
import com.sgarcia.commons.services.ServicioGestorInterface;
import com.sgarcia.commons.services.ServicioSrOperadorInterface;
import com.sgarcia.commons.utils.RMIUtils;

/**
 * The Class ServicioGestorImpl.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6996834756184252642L;

  /**
   * Instantiates a new servicio gestor impl.
   *
   * @throws RemoteException the remote exception
   * @throws NotBoundException the not bound exception
   */
  public ServicioGestorImpl() throws RemoteException, NotBoundException {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioGestorInterface#subir(com.sgarcia.commons.entities.
   * Fichero)
   */
  @Override
  public String subir(Fichero fichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(Integer.valueOf(fichero.obtenerPropietario()));

    return Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + cliente.getRepositorioId();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioGestorInterface#bajar(int, java.lang.String)
   */
  @Override
  public boolean bajar(int clienteId, String nombreFichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(clienteId);

    ServicioSrOperadorInterface servicioSrOperador =
        ((ServicioSrOperadorInterface) RMIUtils.getServiceByName(
            Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + cliente.getRepositorioId()));

    boolean downloaded = servicioSrOperador.bajarFicheroCliente(
        Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + cliente.getId(), cliente.getId(),
        nombreFichero);

    return downloaded;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioGestorInterface#borrar(com.sgarcia.commons.entities.
   * Fichero)
   */
  @Override
  public String borrar(Fichero fichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(Integer.valueOf(fichero.obtenerPropietario()));

    return Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + cliente.getRepositorioId();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.sgarcia.commons.services.ServicioGestorInterface#listar(int)
   */
  @Override
  public List<String> listar(int id) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(id);

    ServicioSrOperadorInterface servicioSrOperador =
        ((ServicioSrOperadorInterface) RMIUtils.getServiceByName(
            Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + cliente.getRepositorioId()));

    List<String> listaFicheros = servicioSrOperador.listarFicherosCliente(id);

    return listaFicheros;
  }

}
