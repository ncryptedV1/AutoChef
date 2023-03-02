package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.utils.Formats;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleInputService {

  private static final Scanner consoleInputScanner =
      System.console() == null ? new Scanner(System.in) : null;

  public static Integer getInteger(int lowerBound, int upperBound, String question) {
    return getInputWithType(userInput -> {
      try {
        Integer choice = Integer.parseInt(userInput);
        if (choice < lowerBound || choice > upperBound) {
          throw new IllegalArgumentException(
              "Die Auswahl muss kleiner größer als " + lowerBound + " und kleiner als " + upperBound
                  + " sein!");
        }
        return choice;
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException();
      }
    }, question);
  }

  public static LocalDate getDate(LocalDate after, LocalDate before, String question) {
    return getInputWithType(userInput -> {
      try {
        LocalDate date = LocalDate.parse(userInput, Formats.DATE_FORMAT);
        if (after != null && !date.isAfter(after)) {
          throw new IllegalArgumentException(
              "Das Datum muss nach dem " + Formats.DATE_FORMAT.format(after) + " liegen!");
        }
        if (before != null && !date.isBefore(before)) {
          throw new IllegalArgumentException(
              "Das Datum muss vor dem " + Formats.DATE_FORMAT.format(before) + " liegen!");
        }
        return date;
      } catch (DateTimeParseException e) {
        throw new IllegalArgumentException();
      }
    }, question);
  }

  private static <T> T getInputWithType(Function<String, T> transformFunction, String question) {
    T choice = null;
    while (choice == null) {
      ConsoleOutputService.rawOut(question);
      String userInput = readLine();
      try {
        choice = transformFunction.apply(userInput);
      } catch (IllegalArgumentException ex) {
        // this is also a parent class of NumberFormatException -> in case of non-integer user input
        ConsoleOutputService.rawErr(
            "'" + userInput + "' ist keine gültige Option, versuche es erneut!");
        if (ex.getMessage() != null) {
          ConsoleOutputService.rawErr(ex.getMessage());
        }
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
