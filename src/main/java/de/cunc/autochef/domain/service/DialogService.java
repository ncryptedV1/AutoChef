package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.aggregate.Meal;
import de.cunc.autochef.domain.aggregate.MealPlan;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.util.Formats;
import de.cunc.autochef.domain.util.io.ConsoleInputReader;
import de.cunc.autochef.domain.util.io.DialogInputParser;
import de.cunc.autochef.domain.util.io.ConsoleOutputService;
import de.cunc.autochef.domain.util.io.InputParser;
import de.cunc.autochef.domain.util.io.InputReader;
import de.cunc.autochef.domain.util.io.OutputService;
import de.cunc.autochef.domain.util.web.ChefkochRecipeFetcher;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DialogService {

  private static final Random random = new Random();
  private DialogState currentState;
  private final RecipeRepository recipeRepository;
  OutputService outputService;
  InputParser inputParser;

  public DialogService(RecipeRepository recipeRepository, OutputService outputService, InputParser inputParser) {
    this.recipeRepository = recipeRepository;
    this.outputService = outputService;
    this.inputParser = inputParser;
  }

  public void startDialog() {
    outputService.rawOut(
        "Willkommen bei AutoChef, deinem persönlichem Freund und Helfer bei der Rezept- & Einkauflisten-Planung!");
    startMain();
  }

  public void startMain() {
    currentState = DialogState.MAIN;

    outputService.rawOut("");
    outputService.rawOut("Wie kann ich dir weiterhelfen?");
    int option = offerOptions("Rezepte anzeigen", "Rezept hinzufügen",
        "Mahlzeiten-Plan generieren");
    if (option == 1) {
      showRecipes();
    } else if (option == 2) {
      addRecipe();
    } else if (option == 3) {
      startMealPlanGeneration();
    }
  }

  private void showRecipes() {
    currentState = DialogState.SHOW_RECIPES;

    outputService.rawOut("Folgende Rezepte sind aktuell in unserer Datenbank:");
    outputService.rawOut("Möchtest du zu einem davon mehr erfahren?");
    List<Recipe> recipes = recipeRepository.getRecipes();

    List<String> options = new ArrayList<>();
    options.add("Nein");
    options.addAll(recipes.stream().map(Recipe::getName).toList());
    int option = offerOptions(options);

    if (option > 1) {
      Recipe recipe = recipes.get(option - 2);
      outputService.rawOut(recipe.toString());
    }

    startMain();
  }

  private void addRecipe() {
    currentState = DialogState.ADD_RECIPE;

    outputService.rawOut(
        "Hier kannst du sehr einfach ein Rezept von Chefkoch in deine Bibliothek mit aufnehmen.");
    String url = inputParser.getString(userInput -> {
          if (!userInput.matches("https://www.chefkoch.de/rezepte/\\d+/.*\\.html")) {
            throw new IllegalArgumentException("Das ist kein Link auf ein Chefkoch-Rezept!");
          }
          return userInput;
        },
        "Welches Rezept würdest du gerne hinzufügen? (https://www.chefkoch.de/rezepte/XXX/XXX.html)");
    Recipe recipe = ChefkochRecipeFetcher.getRecipe(url);
    recipeRepository.saveRecipe(recipe);
    outputService.rawOut("Folgendes Rezept wurde erfolgreich abgespeichert:");
    outputService.rawOut(recipe.toString());

    startMain();
  }

  private void startMealPlanGeneration() {
    currentState = DialogState.MEAL_PLAN_GENERATION;

    outputService.rawOut("Wir generieren jetzt zusammen einen Mahlzeiten-Plan. :D");
    LocalDate startDate = inputParser.getDate(null, null,
        "Wann soll der Plan beginnen? (DD.MM.YYYY)");
    LocalDate endDate = inputParser.getDate(startDate, null,
        "Bis wann soll der Plan gehen (exklusiv)? (DD.MM.YYYY)");
    int people = inputParser.getInteger(1, 99,
        "Für wie viele Leute soll der Plan generiert werden?");
    int days = startDate.until(endDate).getDays();
    outputService.rawOut("Ok, ich generiere einen Plan für " + days + " Tage...");

    List<Recipe> recipes = recipeRepository.getRecipes();
    List<Meal> meals = new ArrayList<>();
    for (int i = 0; i < days; i++) {
      meals.add(new Meal(recipes.get(random.nextInt(recipes.size())), people));
    }

    MealPlan mealPlan = new MealPlan(meals, startDate, endDate);

    startPostMealPlanGeneration(mealPlan, recipes);
  }

  private void startPostMealPlanGeneration(MealPlan mealPlan, List<Recipe> allRecipes) {
    currentState = DialogState.POST_MEAL_PLAN_GENERATION;

    LocalDate startDate = mealPlan.getStart();
    boolean userAdapting = true;

    while (userAdapting) {
      outputService.rawOut("Das ist der aktuelle Plan:");
      outputService.rawOut("Möchtest du eins der Rezepte austauschen?");
      List<String> options = new ArrayList<>();
      options.add("Nein");
      for (int day = 0; day < mealPlan.getDays(); day++) {
        LocalDate date = startDate.plusDays(day);
        options.add(
            Formats.DATE_FORMAT.format(date) + ": " + mealPlan.getMeals().get(day).getRecipe()
                .getName());
      }
      int option = offerOptions(options);

      userAdapting = option > 1;
      if (userAdapting && allRecipes.size() > 1) {
        List<Recipe> otherRecipes = new ArrayList<>(allRecipes);
        otherRecipes.remove(mealPlan.getMeals().get(option - 2).getRecipe());
        mealPlan.getMeals().get(option - 2)
            .setRecipe(otherRecipes.get(random.nextInt(otherRecipes.size())));
      }
    }

    outputService.rawOut("Perfekt, wir haben einen Mahlzeiten-Plan!");
    outputService.rawOut("Was möchtest du mit ihm machen?");
    int option = offerOptions("In der Konsole ausgeben", "In die Zwischenablage kopieren");
    if (option == 1) {
      outputService.rawOut(mealPlan.toString());
    } else if (option == 2) {
      Toolkit.getDefaultToolkit().getSystemClipboard()
          .setContents(new StringSelection(mealPlan.toString()), null);
    }

    startMain();
  }

  public DialogState getCurrentState() {
    return currentState;
  }

  private int offerOptions(String... options) {
    return offerOptions(Arrays.asList(options));
  }

  private int offerOptions(List<String> options) {
    for (int idx = 0; idx < options.size(); idx++) {
      outputService.rawOut("[" + (idx + 1) + "] " + options.get(idx));
    }
    return inputParser.getInteger(1, options.size(), "Auswahl:");
  }

  public enum DialogState {
    MAIN, SHOW_RECIPES, ADD_RECIPE, MEAL_PLAN_GENERATION, POST_MEAL_PLAN_GENERATION
  }
}
