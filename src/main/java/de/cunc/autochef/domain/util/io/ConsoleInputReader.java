package de.cunc.autochef.domain.util.io;

import java.util.Scanner;

public class ConsoleInputReader {

  private static final Scanner consoleInputScanner =
      System.console() == null ? new Scanner(System.in) : null;

  public static String readLine() {
    if (System.console() != null) {
      return System.console().readLine();
    }
    return consoleInputScanner.nextLine();
  }
}
