package services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.sgarcia.commons.services.ServicioAutenticacionInterface;
import com.sgarcia.commons.services.ServicioDiscoClienteInterface;

/**
 * @author sergio
 *
 */
public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {

  private Map<Integer, String> registroClientes = new HashMap<Integer, String>();
  private Map<Integer, ServicioDiscoClienteInterface> serviciosDiscoCliente =
      new HashMap<Integer, ServicioDiscoClienteInterface>();

  @Override
  public int registrar(String nombre) throws RemoteException {
    System.out.println("Registro: " + nombre);

    int sessionId = getSesionId();
    
    registroClientes.put(sessionId, nombre);

    return sessionId;
  }

  @Override
  public boolean autenticar(int id) throws RemoteException {
    System.out.println("Autenticación: " + id);
    
    if(!registroClientes.containsKey(id)){
      throw new RuntimeException("Usuario no registrado!");
    }

    Registry registry = LocateRegistry.getRegistry();
    try {
      ServicioDiscoClienteInterface servicioDiscoCliente = (ServicioDiscoClienteInterface) registry
          .lookup("rmi://127.0.0.1:9001/servicioDiscoCliente/" + id);

      serviciosDiscoCliente.put(id, servicioDiscoCliente);

      return true;
    } catch (NotBoundException e) {
      System.out.println("No ha podido identificarse servicio disco de cliente.");
    }

    return false;
  }

  private int getSesionId() {
    return new AtomicInteger().incrementAndGet();
  }

}
