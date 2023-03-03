package de.cunc.autochef.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Meal {

  private Recipe recipe;
  private int adjustedNumberOfPeople;

  public Meal(Recipe recipe, int adjustedNumberOfPeople) {
    this.recipe = recipe;
    this.adjustedNumberOfPeople = adjustedNumberOfPeople;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public int getAdjustedNumberOfPeople() {
    return adjustedNumberOfPeople;
  }

  public GroceryList getIngredients() {
    List<GroceryItem> items = recipe.getIngredients().getItems().stream().map(
        item -> new GroceryItem(item.getIngredient(),
            item.getQuantity().multiply(adjustedNumberOfPeople), item.getUnit())).toList();
    return new GroceryList(items);
  }

  @Override
  public String toString() {
    return "Mahlzeit (" + adjustedNumberOfPeople + " Personen): " + recipe.getName() + ":\n"
        + getIngredients().toString() + "\n"
        + "Zubereitung:\n"
        + recipe.getRecipeSteps().stream().map(step -> step.toString())
        .collect(Collectors.joining("\n"));
  }
}