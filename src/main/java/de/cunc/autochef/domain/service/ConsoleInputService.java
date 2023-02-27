package de.cunc.autochef.domain.service;

import java.util.Scanner;

public class ConsoleInputService {

  private static final Scanner consoleInputScanner =
      System.console() == null ? new Scanner(System.in) : null;

  public static int getOptionSelection(int upperBound) {
    Integer choice = null;
    while (choice == null) {
      ConsoleOutputService.rawOut("Auswahl:");
      String userInput = readLine();
      try {
        choice = Integer.parseInt(userInput);
        if (choice < 1 || choice > upperBound) {
          choice = null;
          throw new IllegalArgumentException();
        }
      } catch (IllegalArgumentException ex) {
        // this is also a parent class of NumberFormatException -> in case of non-integer user input
        ConsoleOutputService.rawErr(
            "'" + userInput + "' ist keine gültige Option, versuche es erneut!");
      }
    }
    return choice;
  }

  private static String readLine() {
    if (consoleInputScanner == null) {
      return System.console().readLine();
    } else {
      return consoleInputScanner.nextLine();
    }
  }
}
