package services;

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

public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface {

  private static final long serialVersionUID = -6996834756184252642L;

  public ServicioGestorImpl() throws RemoteException, NotBoundException {
    super();
  }

  @Override
  public String subir(Fichero fichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(Integer.valueOf(fichero.obtenerPropietario()));

    return Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + cliente.getRepositorioId();
  }

  @Override
  public boolean bajar(int clienteId, String nombreFichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(clienteId);

    ServicioSrOperadorInterface servicioSrOperador =
        ((ServicioSrOperadorInterface) RMIUtils.getServiceByName(
            Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + cliente.getRepositorioId()));
    
    boolean downloaded = servicioSrOperador.bajarFicheroCliente(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + cliente.getId(), cliente.getId(), nombreFichero);
    
    return downloaded;
  }

  @Override
  public String borrar(Fichero fichero) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(Integer.valueOf(fichero.obtenerPropietario()));

    return Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + cliente.getRepositorioId();
  }

  @Override
  public List<String> listar(int id) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.getCliente(id);

    ServicioSrOperadorInterface servicioSrOperador = ((ServicioSrOperadorInterface) RMIUtils
        .getServiceByName(Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + cliente.getRepositorioId()));

    List<String> listaFicheros = servicioSrOperador.listarFicherosCliente(id);

    return listaFicheros;
  }

}
