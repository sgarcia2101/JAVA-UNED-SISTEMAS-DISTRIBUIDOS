package com.sgarcia.commons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

  public static void crearCarpeta(String carpetaDir) {
    File carpeta = new File(carpetaDir);
    if (!carpeta.exists()) {
      carpeta.mkdir();
    }
  }

  public static List<File> listarFicherosCarpeta(String carpetaDir) {
    File carpeta = new File(carpetaDir);
    File[] ficheros = carpeta.listFiles();
    return new ArrayList<>(Arrays.asList(ficheros));
  }
}
