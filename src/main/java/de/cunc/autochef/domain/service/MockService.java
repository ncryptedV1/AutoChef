package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MockService {

  private static final Random random = new Random();

  public static List<GroceryItem> generateGroceryItems() {
    GroceryItem item1 = new GroceryItem(new Ingredient("Banane"), new Quantity(1), Unit.GRAM);
    GroceryItem item2 = new GroceryItem(new Ingredient("Pineapple"), new Quantity(0.2),
        Unit.KILOGRAM);
    GroceryItem item3 = new GroceryItem(new Ingredient("Orange juice"), new Quantity(0.1),
        Unit.LITER);
    GroceryItem item4 = new GroceryItem(new Ingredient("Apple"), new Quantity(1), Unit.PIECE);
    GroceryItem item5 = new GroceryItem(new Ingredient("Nutella"), new Quantity(2),
        Unit.TABLESPOON);
    return Arrays.asList(item1, item2, item3, item4, item5);
  }

  public static List<RecipeStep> generateRecipeSteps(List<GroceryItem> groceryItems) {
    RecipeStep recipeStep1 = new RecipeStep(1,
        "Cut some banana, apple and pineapple as the basis for this salad.",
        getSample(groceryItems));
    RecipeStep recipeStep2 = new RecipeStep(2, "Add orange juice to make it more juicy.",
        getSample(groceryItems));
    RecipeStep recipeStep3 = new RecipeStep(3, "Add a bit of Nutella for making it look beautiful.",
        getSample(groceryItems));
    return Arrays.asList(recipeStep1, recipeStep2, recipeStep3);
  }

  public static Recipe generateRecipe(List<RecipeStep> recipeSteps) {
    return new Recipe("Sugar-free fruit salad", recipeSteps);
  }

  public static Meal generateMeal(Recipe recipe) {
    return new Meal(recipe, 2);
  }

  public static MealPlan generateMealPlan(List<Meal> meals) {
    LocalDate startDate = LocalDate.of(2023, 2, 20);
    LocalDate endDate = LocalDate.of(2023, 2, 26);
    return new MealPlan(meals, startDate, endDate);
  }

  private static <T> List<T> getSample(List<T> list) {
    int sampleSize = random.nextInt(1, list.size());
    List<T> result = new ArrayList<>(list);
    result = result.subList(0, sampleSize);
    return result;
  }
}
