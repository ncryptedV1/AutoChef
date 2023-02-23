package de.cunc.autochef.domain.entities;

public class RecipeStep {

  int step;
  GroceryList ingredients;
  String description;

  public RecipeStep(int step, String description, GroceryListEntry... ingredients) {
    // todo: check step > 0
    this.step = step;
    this.ingredients = new GroceryList();
    for (GroceryListEntry entry : ingredients) {
      this.ingredients.addItem(entry);
    }

    this.description = description;
  }
}
