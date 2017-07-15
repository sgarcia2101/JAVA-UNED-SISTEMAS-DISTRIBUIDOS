package com.sgarcia.commons.utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.sgarcia.commons.constants.Constants;

public class RMIUtils {

  // This method starts an RMI registry on the local host, if it does not already exist
  public static void startRegistry() throws RemoteException {
    try {
      Registry registry = LocateRegistry.getRegistry(Constants.PORT);
      registry.list(); // This call throws a exception if the registry does not already exist

    } catch (RemoteException ex) {
      // No valid registry at that port.
      LocateRegistry.createRegistry(Constants.PORT);

    }
  }

  public static void registryService(String name, Remote service)
      throws RemoteException, MalformedURLException {
    Naming.rebind("rmi://" + Constants.HOST + ":" + Constants.PORT + "/" + name, service);
  }

  public static void unregistryService(String name)
      throws RemoteException, MalformedURLException, NotBoundException {
    Naming.unbind("rmi://" + Constants.HOST + ":" + Constants.PORT + "/" + name);
  }

  public static Remote getServiceByName(String name) throws RemoteException {
    try {
      Registry registry = LocateRegistry.getRegistry();
      return registry.lookup(name);
    } catch (NotBoundException e) {
      throw new RuntimeException("No ha podido obtener el servicio " + name + ".");
    }
  }

  public static boolean existsService(String name) throws RemoteException {
    try {
      Registry registry = LocateRegistry.getRegistry();
      registry.lookup(name);
      return true;
    } catch (NotBoundException e) {
      return false;
    }
  }

}
