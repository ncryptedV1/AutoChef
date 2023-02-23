package de.cunc.autochef.domain.entities;

public class RecipeStep {

  int step;
  GroceryList ingredients;
  String description;

  public RecipeStep(int step, GroceryList ingredients, String description) {
    // todo: check step > 0
    this.step = step;
    this.ingredients = ingredients;
    this.description = description;
  }
}
