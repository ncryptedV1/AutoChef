package de.cunc.autochef;

import de.cunc.autochef.domain.entities.GroceryList;
import de.cunc.autochef.domain.entities.GroceryListEntry;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.MealList;
import de.cunc.autochef.domain.entities.MealPlan;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    // setup groceries
    GroceryListEntry item1 = new GroceryListEntry(new Ingredient("Banane"), new Quantity(1), Unit.GRAM);
    GroceryListEntry item2 = new GroceryListEntry(new Ingredient("Pineapple"), new Quantity(0.2), Unit.KILOGRAM);
    GroceryListEntry item3 = new GroceryListEntry(new Ingredient("Orange juice"), new Quantity(0.1), Unit.LITER);
    GroceryListEntry item4 = new GroceryListEntry(new Ingredient("Apple"), new Quantity(1), Unit.PIECE);
    GroceryListEntry item5 = new GroceryListEntry(new Ingredient("Nutella"), new Quantity(2), Unit.TABLESPOON);

    // list of groceries for recipe steps
    GroceryList recipeStep1Ingredients = new GroceryList();
    recipeStep1Ingredients.addItem(item1);
    recipeStep1Ingredients.addItem(item2);
    recipeStep1Ingredients.addItem(item3);

    GroceryList recipeStep2Ingredients = new GroceryList();
    recipeStep2Ingredients.addItem(item4);

    GroceryList recipeStep3Ingredients = new GroceryList();
    recipeStep3Ingredients.addItem(item5);

    // setup recipe steps
    RecipeStep recipeStep1 = new RecipeStep(0, recipeStep1Ingredients,
        "Cut some banane, apple and pineapple as the basis for this salad.");
    RecipeStep recipeStep2 = new RecipeStep(1, recipeStep2Ingredients,
        "Add orange juice to make it more juicy.");
    RecipeStep recipeStep3 = new RecipeStep(2, recipeStep3Ingredients,
        "Add a bit of Nutella for making it look beautiful.");

    // setup recipe for
    List<RecipeStep> recipeStepList = new ArrayList<RecipeStep>();
    recipeStepList.add(recipeStep1);
    recipeStepList.add(recipeStep2);
    recipeStepList.add(recipeStep3);
    Recipe recipe1 = new Recipe("Sugar-free fruit salad", recipeStepList);

    // setup meal
    Meal meal1 = new Meal(recipe1, 2);
    List<Meal> meals = new ArrayList<Meal>();
    meals.add(meal1);

    // setup meal plan
    MealList mealList = new MealList(meals);
    LocalDate startDate = LocalDate.of(2023, 02, 20);
    LocalDate endDate = LocalDate.of(2023, 02, 26);
    MealPlan mealPlan = new MealPlan(mealList, startDate, endDate);

    System.out.println(mealPlan.toString());
  }
}