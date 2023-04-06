package de.cunc.autochef.domain.service.io;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {

  private static final Scanner consoleInputScanner =
      System.console() == null ? new Scanner(System.in) : null;

  public String readLine() {
    if (System.console() != null) {
      return System.console().readLine();
    }
    return consoleInputScanner.nextLine();
  }
}
