package de.cunc.autochef.domain.service.io;

import de.cunc.autochef.domain.util.Formats;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

public class DialogInputParser implements InputParser {

  private final InputReader inputReader;
  private final OutputService outputService;

  public DialogInputParser(InputReader inputReader, OutputService outputService) {
    this.inputReader = inputReader;
    this.outputService = outputService;
  }

  public Integer getInteger(Integer lowerBound, Integer upperBound, String question) {
    return getInputWithType(userInput -> {
      try {
        int choice = Integer.parseInt(userInput);
        if (lowerBound != null && choice < lowerBound) {
          throw new IllegalArgumentException(
              "Die Auswahl muss größer als " + (lowerBound - 1) + " sein!");
        }
        if (upperBound != null && choice > upperBound) {
          throw new IllegalArgumentException(
              "Die Auswahl muss kleiner als " + (upperBound + 1) + " sein!");
        }
        return choice;
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException();
      }
    }, question);
  }

  public LocalDate getDate(LocalDate after, LocalDate before, String question) {
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

  public String getString(Function<String, String> validator, String question) {
    return getInputWithType(validator, question);
  }

  private <T> T getInputWithType(Function<String, T> transformFunction, String question) {
    T choice = null;
    while (choice == null) {
      outputService.rawOut(question);
      String userInput = this.inputReader.readLine();
      try {
        choice = transformFunction.apply(userInput);
      } catch (IllegalArgumentException ex) {
        // this is also a parent class of NumberFormatException -> in case of non-integer user input
        outputService.rawErr(
            "'" + userInput + "' ist keine gültige Option, versuche es erneut!");
        if (ex.getMessage() != null) {
          outputService.rawErr(ex.getMessage());
        }
      }
    }
    return choice;
  }
}
