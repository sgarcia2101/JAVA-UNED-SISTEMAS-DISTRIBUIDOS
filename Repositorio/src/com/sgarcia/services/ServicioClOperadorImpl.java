package com.sgarcia.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.sgarcia.commons.services.ServicioClOperadorInterface;

public class ServicioClOperadorImpl extends UnicastRemoteObject implements ServicioClOperadorInterface {

  private static final long serialVersionUID = -6039214596140705245L;
  
  private int servidorId;

  public ServicioClOperadorImpl(int servidorId) throws RemoteException {
    super();
    this.servidorId = servidorId;
  }

  public int getServidorId() {
    return servidorId;
  }

  public void setServidorId(int servidorId) {
    this.servidorId = servidorId;
  }

}
