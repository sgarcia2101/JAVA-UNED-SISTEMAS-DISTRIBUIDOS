package com.sgarcia.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.services.ServicioDiscoClienteInterface;

public class ServicioDiscoClienteImpl extends UnicastRemoteObject
    implements ServicioDiscoClienteInterface {

  private static final long serialVersionUID = -4714224212802634239L;

  public ServicioDiscoClienteImpl() throws RemoteException {
    super();
  }

}
