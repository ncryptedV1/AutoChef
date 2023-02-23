package de.cunc.autochef.domain.entities;

public class RecipeStep {

  int step;
  GroceryList ingredients;
  String description;

  public RecipeStep(int step, String description, GroceryItem... ingredients) {
    // todo: check step > 0
    this.step = step;
    this.ingredients = new GroceryList(ingredients);
    this.description = description;
  }

  public String toString() {
    // todo: implement
    return null;
  }
}
