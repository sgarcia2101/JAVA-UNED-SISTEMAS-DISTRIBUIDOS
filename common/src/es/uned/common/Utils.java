package es.uned.common;

import java.net.UnknownHostException;

public class Utils {

  public static final String CODEBASE = "java.rmi.server.codebase";


  public static void setCodeBase(Class<?> c) throws UnknownHostException {
    String ruta = c.getProtectionDomain().getCodeSource().getLocation().toString();

    String path = System.getProperty(CODEBASE);

    if (path != null && !path.isEmpty()) {
      ruta = path + " " + ruta;
    }

    System.setProperty(CODEBASE, ruta);
  }
}
