package de.cunc.autochef.domain.entities;

import java.util.List;
import java.util.Objects;
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

  public GroceryList getGroceryList() {
    List<GroceryItem> items = recipe.getGroceryList().getItems().stream().map(
        item -> new GroceryItem(item.getIngredient(),
            item.getQuantity().multiply(adjustedNumberOfPeople), item.getUnit())).toList();
    return new GroceryList(items);
  }

  @Override
  public String toString() {
    return "Mahlzeit (" + adjustedNumberOfPeople + " Personen): " + recipe.getName() + ":\n"
        + getGroceryList().toString() + "\n"
        + "Zubereitung:\n"
        + recipe.getRecipeSteps().stream().map(RecipeStep::toString)
        .collect(Collectors.joining("\n"));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Meal meal = (Meal) o;
    return adjustedNumberOfPeople == meal.adjustedNumberOfPeople && Objects.equals(recipe,
        meal.recipe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipe, adjustedNumberOfPeople);
  }
}