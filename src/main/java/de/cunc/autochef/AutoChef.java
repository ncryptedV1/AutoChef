package de.cunc.autochef;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.service.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.MockService;
import de.cunc.autochef.domain.service.PersistenceService;
import java.util.Arrays;
import java.util.List;

public class AutoChef {

  public static void main(String[] args) {
    ConsoleOutputService.info("Starte...");

    // generate mock data
    List<GroceryItem> groceryItems = MockService.generateGroceryItems();
    List<RecipeStep> recipeSteps = MockService.generateRecipeSteps();
    Recipe recipe = MockService.generateRecipe(groceryItems, recipeSteps);
    Meal meal = MockService.generateMeal(recipe);
    List<Meal> meals = Arrays.asList(meal);
    MealPlan mealPlan = MockService.generateMealPlan(meals);

    ConsoleOutputService.info("Mahlzeiten-Plan: " + mealPlan.toString());

    PersistenceService.saveRecipe(recipe);
    DialogService.startDialog();

    ConsoleOutputService.info("Dialog Endstatus: " + DialogService.getCurrentState());
  }
}