package de.cunc.autochef.domain.service;

public class DialogService {

  public enum DialogState {
    MAIN, MEAL_PLAN_GENERATION
  }

  private static DialogState currentState;

  public static void startDialog() {
    currentState = DialogState.MAIN;

    ConsoleOutputService.rawOut(
        "Willkommen bei AutoChef, deinem persönlichem Freund und Helfer bei der Rezept- & Einkauflisten-Planung!");
    ConsoleOutputService.rawOut("Wie kann ich dir weiterhelfen?");
    int option = offerOptions("Rezepte anzeigen", "Mahlzeiten-Plan generieren");
    if(option == 1) {
      // ToDo: implementation
    } else if(option == 2) {
      startMealPlanGeneration();
    }
  }

  public static void startMealPlanGeneration() {
    currentState = DialogState.MEAL_PLAN_GENERATION;

    ConsoleOutputService.rawOut("Wir generieren jetzt einen Mahlzeiten-Plan zusammen. :D");
    ConsoleOutputService.rawOut("Wann soll der Plan beginnen? (DD.MM.YYYY)");
    // ToDo: implementation body
  }

  public static DialogState getCurrentState() {
    return currentState;
  }

  private static int offerOptions(String... options) {
    for (int idx = 0; idx < options.length; idx++) {
      ConsoleOutputService.rawOut("[" + (idx + 1) + "] " + options[idx]);
    }
    return ConsoleInputService.getOptionSelection(options.length);
  }
}
