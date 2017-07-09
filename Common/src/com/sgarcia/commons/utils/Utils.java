package com.sgarcia.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Utils {

  public static final String CODEBASE = "java.rmi.server.codebase";
  public static final int PORT = 1099;

  public static Registry createRegistry() throws RemoteException{
    return LocateRegistry.createRegistry(PORT);
  }
  
  public static String getIp() throws UnknownHostException{
    return InetAddress.getLocalHost().getHostAddress();
  }
  
  public static void registryService(Registry registry, String name, int port, Remote service)
      throws RemoteException, AccessException, UnknownHostException {
    Remote remote = UnicastRemoteObject.exportObject(service, port);
    registry.rebind("rmi://" + getIp() + ":" + port + "/" + name, remote);
  }

  public static void unregistryService(Registry registry, String name, int port, Remote service)
          throws RemoteException, NotBoundException, AccessException, NoSuchObjectException, UnknownHostException {
    registry.unbind("rmi://" + getIp() + ":" + port + "/" + name);
    UnicastRemoteObject.unexportObject(service, true);
  }

//  public static void setCodeBase(Class<?> c) throws UnknownHostException {
//    String ruta = c.getProtectionDomain().getCodeSource().getLocation().toString();
//
//    String path = System.getProperty(CODEBASE);
//
//    if (path != null && !path.isEmpty()) {
//      ruta = path + " " + ruta;
//    }
//
//    System.setProperty(CODEBASE, ruta);
//  }
}
