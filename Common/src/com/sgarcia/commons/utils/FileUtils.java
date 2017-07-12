package com.sgarcia.commons.utils;

import java.io.File;

public class FileUtils {
  
  public static void crearCarpeta(String carpetaDir) {
    File carpeta = new File(carpetaDir);
    if (!carpeta.exists()) {
      carpeta.mkdir();
    }
  }
}
