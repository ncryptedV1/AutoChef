package de.cunc.autochef.domain.entities;

public class RecipeStep {

  private int step;
  private String description;
  private GroceryList ingredients;

  public RecipeStep(int step, String description, GroceryItem... ingredients) {
    if(step <= 0) {
      throw new IllegalArgumentException("step must be greater than 0");
    }

    this.step = step;
    this.description = description;
    this.ingredients = new GroceryList(ingredients);
  }

  public int getStep() {
    return step;
  }

  public String getDescription() {
    return description;
  }

  public GroceryList getIngredients() {
    return ingredients;
  }

  @Override
  public String toString() {
    return "RecipeStep{" +
        "step=" + step +
        ", description='" + description + '\'' +
        ", ingredients=" + ingredients +
        '}';
  }
}
