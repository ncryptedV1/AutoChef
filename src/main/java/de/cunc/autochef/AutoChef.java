package de.cunc.autochef;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.service.MockService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AutoChef {

  private static final Logger logger;

  static {
    try (InputStream inputStream = AutoChef.class.getResourceAsStream("/logging.properties")) {
      LogManager.getLogManager().readConfiguration(inputStream);
      logger = Logger.getLogger("AutoChef");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    logger.info("Starting...");

    // generate mock data
    List<GroceryItem> groceryItems = MockService.generateGroceryItems();
    List<RecipeStep> recipeSteps = MockService.generateRecipeSteps(groceryItems);
    Recipe recipe = MockService.generateRecipe(recipeSteps);
    Meal meal = MockService.generateMeal(recipe);
    List<Meal> meals = Arrays.asList(meal);
    MealPlan mealPlan = MockService.generateMealPlan(meals);

    logger.info(mealPlan.toString());
  }
}