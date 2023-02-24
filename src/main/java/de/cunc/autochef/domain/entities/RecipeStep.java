package de.cunc.autochef.domain.entities;

public class RecipeStep {

  int number;
  GroceryList ingredients;
  String description;

  public RecipeStep(int number, String description, GroceryItem... ingredients) {
    if (number < 0) {
      throw new IllegalArgumentException("number must be greater or equal to 0");
    }

    this.number = number;
    this.ingredients = new GroceryList(ingredients);
    this.description = description;
  }

  public String toString() {
    // todo: implement
    return null;
  }
}
