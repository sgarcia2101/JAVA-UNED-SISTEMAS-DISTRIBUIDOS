package services;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.constants.Constants;
import com.sgarcia.commons.entities.Cliente;
import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.services.ServicioDatosInterface;
import com.sgarcia.commons.services.ServicioSrOperadorInterface;
import com.sgarcia.commons.utils.RMIUtils;

/**
 * @author sergio
 *
 */
public class ServicioAutenticacionImpl extends UnicastRemoteObject
    implements ServicioAutenticacionInterface {

  private static final long serialVersionUID = 7102562566989123551L;

  public ServicioAutenticacionImpl() throws RemoteException, NotBoundException {
    super();
  }

  @Override
  public int registrarCliente(String nombre) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    Cliente cliente = servicioDatos.registrarCliente(nombre);

    ServicioSrOperadorInterface servicioSrOperador =
        ((ServicioSrOperadorInterface) RMIUtils.getServiceByName(
            Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + File.separator + cliente.getRepositorioId()));

    servicioSrOperador.crearCarpetaCliente(cliente.getId());

    return cliente.getId();
  }

  @Override
  public int registrarRepositorio(String nombre) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    return servicioDatos.registrarRepositorio(nombre);
  }

  @Override
  public boolean autenticarCliente(int id) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    if (servicioDatos.getCliente(id) == null) {
      throw new RuntimeException("Usuario no registrado.");
    }

    if (!RMIUtils.existsService(Constants.NOMBRE_SERVICIO_DISCO_CLIENTE + "/" + id)) {
      throw new RuntimeException("Servicio disco no disponible.");
    }

    return true;
  }

  @Override
  public boolean autenticarRepositorio(int id) throws RemoteException {
    ServicioDatosInterface servicioDatos =
        ((ServicioDatosInterface) RMIUtils.getServiceByName(Constants.NOMBRE_SERVICIO_DATOS));

    if (servicioDatos.getRepositorio(id) == null) {
      throw new RuntimeException("Repositorio no registrado.");
    }

    if (!RMIUtils.existsService(Constants.NOMBRE_SERVICIO_CLIENTE_OPERADOR + "/" + id)) {
      throw new RuntimeException("Servicio cliente-operador no disponible.");
    }

    if (!RMIUtils.existsService(Constants.NOMBRE_SERVICIO_SERVIDOR_OPERADOR + "/" + id)) {
      throw new RuntimeException("Servicio servidor-operador no disponible.");
    }

    return true;
  }

}
