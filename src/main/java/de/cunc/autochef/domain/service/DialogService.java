package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.utils.Formats;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DialogService {

  public enum DialogState {
    MAIN, RECIPES, MEAL_PLAN_GENERATION, POST_MEAL_PLAN_GENERATION
  }

  private static DialogState currentState;
  private static final Random random = new Random();

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

  private static void showRecipes() {
    currentState = DialogState.RECIPES;

    ConsoleOutputService.rawOut("Folgende Rezepte sind aktuell in unserer Datenbank:");
    ConsoleOutputService.rawOut("Möchtest du zu einem davon mehr erfahren?");
    List<Recipe> recipes = PersistenceService.getRecipes();

    List<String> options = new ArrayList<>();
    options.add("Nein");
    options.addAll(recipes.stream().map(recipe -> recipe.getName()).toList());
    int option = offerOptions(options);

    if (option > 1) {
      Recipe recipe = recipes.get(option - 2);
      ConsoleOutputService.rawOut(recipe.toString());
    }
    ConsoleOutputService.rawOut("");

    startMain();
  }

  private static void startMealPlanGeneration() {
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
    for (int i = 0; i < days; i++) {
      meals.add(new Meal(recipes.get(random.nextInt(recipes.size())), people));
    }

    MealPlan mealPlan = new MealPlan(meals, startDate, endDate);

    startPostMealPlanGeneration(mealPlan, recipes);
  }

  private static void startPostMealPlanGeneration(MealPlan mealPlan, List<Recipe> allRecipes) {
    currentState = DialogState.POST_MEAL_PLAN_GENERATION;

    LocalDate startDate = mealPlan.getStart();
    int days = startDate.until(mealPlan.getEnd()).getDays();

    boolean userAdapting = true;
    while (userAdapting) {
      ConsoleOutputService.rawOut("Das ist der aktuelle Plan:");
      ConsoleOutputService.rawOut("Möchtest du eins der Rezepte austauschen?");
      List<String> options = new ArrayList<>();
      options.add("Nein");
      for (int day = 0; day < days; day++) {
        LocalDate date = startDate.plusDays(day);
        options.add(
            Formats.DATE_FORMAT.format(date) + ": " + mealPlan.getMealList().get(day).getRecipe()
                .getName());
      }
      int option = offerOptions(options);

      userAdapting = option > 1;
      if (userAdapting) {
        mealPlan.getMealList().get(option - 2)
            .setRecipe(allRecipes.get(random.nextInt(allRecipes.size())));
      }
    }

    ConsoleOutputService.rawOut("Perfekt, wir haben einen Mahlzeiten-Plan!");
    ConsoleOutputService.rawOut("Was möchtest du mit ihm machen?");
    int option = offerOptions("In der Konsole ausgeben", "In die Zwischenablage kopieren");
    if(option == 1) {
      // ToDo: implementation body
    } else if(option == 2) {
      // ToDo: implementation body
    }

    startMain();
  }

  public static DialogState getCurrentState() {
    return currentState;
  }

  private static int offerOptions(String... options) {
    return offerOptions(Arrays.asList(options));
  }

  private static int offerOptions(List<String> options) {
    for (int idx = 0; idx < options.size(); idx++) {
      ConsoleOutputService.rawOut("[" + (idx + 1) + "] " + options.get(idx));
    }
    return ConsoleInputService.getInteger(1, options.size(), "Auswahl:");
  }
}
