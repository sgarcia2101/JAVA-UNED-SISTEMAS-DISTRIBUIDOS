package com.sgarcia.commons.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gui {

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // public static String[] input(String name, String[] msgs) {
  // String[] inputs = new String[msgs.length];
  //
  // System.out.println(("=== " + name + " ===");
  //
  // for (int i = 0; i < msgs.length; i++) {
  // inputs[i] = input(msgs[i]);
  // }
  //
  // return inputs;
  // }
  //

  public static String textInput(String msg) {

    System.out.print(msg);

    String line = readLine();

    return line;
  }

  public static int numberInput(String msg) {

    System.out.print(msg);

    int number = Integer.parseInt(readLine());

    return number;
  }

  public static int menu(String[] options) {

    System.out.println();

    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ".- " + options[i]);
    }

    System.out.print("Introduzca una opción: ");

    int option = -1;

    do {
      option = Integer.parseInt(readLine().trim());

      if (option >= options.length + 1 || option <= 0) {
        System.out.print("Introduzca una opción válida: ");
        option = -1;
      }
    } while (option == -1);

    System.out.println();

    return option;
  }

  //
  // private static void System.out.println((String msg) {
  // System.out.println(msg);
  // }
  //
  // private static void newLine() {
  // System.out.println();
  // }
  //
  // private static void out(String msg) {
  // System.out.print(msg);
  // }
  //
  private static String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
