package com.sgarcia.commons.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Class Gui.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class Gui {

  /** The reader. */
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  /**
   * Text input.
   *
   * @param msg the msg
   * @return the string
   */
  public static String textInput(String msg) {

    System.out.print(msg);

    String line = readLine();

    return line;
  }

  /**
   * Number input.
   *
   * @param msg the msg
   * @return the int
   */
  public static int numberInput(String msg) {

    System.out.print(msg);

    int number = Integer.parseInt(readLine());

    return number;
  }

  /**
   * Menu.
   *
   * @param options the options
   * @return the int
   */
  public static int menu(String[] options) {

    System.out.println();

    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ".- " + options[i]);
    }

    System.out.print("Introduzca una opción: ");

    int option = -1;

    do {
      try {
        option = Integer.parseInt(readLine().trim());
      } catch (NumberFormatException e) {
      }

      if (option >= options.length + 1 || option <= 0) {
        System.out.print("Introduzca una opción válida: ");
        option = -1;
      }
    } while (option == -1);

    System.out.println();

    return option;
  }

  /**
   * Read line.
   *
   * @return the string
   */
  private static String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
