package de.cunc.autochef.domain.entities.recipestep;

import de.cunc.autochef.domain.entities.grocerylist.GroceryList;

public class RecipeStep {

  int step;
  GroceryList ingredients;
  String description;

  public RecipeStep(int step, GroceryList ingredients, String description) {
    this.step = step;
    this.ingredients = ingredients;
    this.description = description;
  }
}
