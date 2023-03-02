package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.utils.Formats;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialogService {

  public enum DialogState {
    MAIN, MEAL_PLAN_GENERATION
  }

  private static DialogState currentState;

  public static void startDialog() {
    ConsoleOutputService.rawOut(
        "Willkommen bei AutoChef, deinem persönlichem Freund und Helfer bei der Rezept- & Einkauflisten-Planung!");
    startMain();
  }

  public static void startMain() {
    currentState = DialogState.MAIN;

    ConsoleOutputService.rawOut("Wie kann ich dir weiterhelfen?");
    int option = offerOptions("Rezepte anzeigen", "Mahlzeiten-Plan generieren");
    if (option == 1) {
      showRecipes();
    } else if (option == 2) {
      startMealPlanGeneration();
    }
  }

  public static void showRecipes() {
    ConsoleOutputService.rawOut("Folgende Rezepte sind aktuell in unserer Datenbank:");

    List<Recipe> recipes = PersistenceService.getRecipes();
    recipes.forEach(recipe -> ConsoleOutputService.rawOut("- " + recipe.getName()));
    ConsoleOutputService.rawOut("");

    startMain();
  }

  public static void startMealPlanGeneration() {
    currentState = DialogState.MEAL_PLAN_GENERATION;

    ConsoleOutputService.rawOut("Wir generieren jetzt zusammen einen Mahlzeiten-Plan. :D");
    LocalDate startDate = ConsoleInputService.getDate(null, null,
        "Wann soll der Plan beginnen? (DD.MM.YYYY)");
    LocalDate endDate = ConsoleInputService.getDate(startDate, null,
        "Bis wann soll der Plan gehen (exklusiv)? (DD.MM.YYYY)");
    int people = ConsoleInputService.getInteger(1, 99,
        "Für wie viele Leute soll der Plan generiert werden?");
    int days = startDate.until(endDate).getDays();
    ConsoleOutputService.rawOut("Ok, ich generiere einen Plan für " + days + " Tage...");

    List<Recipe> recipes = PersistenceService.getRecipes();
    List<Meal> meals = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < days; i++) {
      meals.add(new Meal(recipes.get(random.nextInt(recipes.size())), people));
    }

    MealPlan mealPlan = new MealPlan(meals, startDate, endDate);
    for (int day = 0; day < days; day++) {
      LocalDate date = startDate.plusDays(day);
      ConsoleOutputService.rawOut(
          Formats.DATE_FORMAT.format(date) + ": " + mealPlan.getMealList().get(day).getRecipe().getName());
    }
    ConsoleOutputService.rawOut("");

    startMain();
  }

  public static DialogState getCurrentState() {
    return currentState;
  }

  private static int offerOptions(String... options) {
    for (int idx = 0; idx < options.length; idx++) {
      ConsoleOutputService.rawOut("[" + (idx + 1) + "] " + options[idx]);
    }
    return ConsoleInputService.getInteger(1, options.length, "Auswahl:");
  }
}
