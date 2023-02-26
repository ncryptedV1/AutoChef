package de.cunc.autochef;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
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

    // setup groceries
    GroceryItem item1 = new GroceryItem(new Ingredient("Banane"), new Quantity(1), Unit.GRAM);
    GroceryItem item2 =
        new GroceryItem(new Ingredient("Pineapple"), new Quantity(0.2), Unit.KILOGRAM);
    GroceryItem item3 =
        new GroceryItem(new Ingredient("Orange juice"), new Quantity(0.1), Unit.LITER);
    GroceryItem item4 = new GroceryItem(new Ingredient("Apple"), new Quantity(1), Unit.PIECE);
    GroceryItem item5 =
        new GroceryItem(new Ingredient("Nutella"), new Quantity(2), Unit.TABLESPOON);

    // setup recipe steps
    RecipeStep recipeStep1 =
        new RecipeStep(0, "Cut some banane, apple and pineapple as the basis for this salad.",
            item1, item2, item3);
    RecipeStep recipeStep2 = new RecipeStep(1, "Add orange juice to make it more juicy.", item4);
    RecipeStep recipeStep3 =
        new RecipeStep(2, "Add a bit of Nutella for making it look beautiful.", item5);

    // setup recipe for
    Recipe recipe1 = new Recipe("Sugar-free fruit salad", recipeStep1, recipeStep2, recipeStep3);

    // setup meal
    Meal meal1 = new Meal(recipe1, 2);
    // setup meal plan
    List<Meal> mealList = Arrays.asList(meal1);
    LocalDate startDate = LocalDate.of(2023, 2, 20);
    LocalDate endDate = LocalDate.of(2023, 2, 26);
    MealPlan mealPlan = new MealPlan(mealList, startDate, endDate);

    logger.info(mealPlan.toString());
  }
}