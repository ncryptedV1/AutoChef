package de.cunc.autochef.domain.entities;

public class Meal {

  private Recipe recipe;
  private int adjustedNumberOfPeople;

  public Meal(Recipe recipe, int adjustedNumberOfPeople) {
    this.recipe = recipe;
    this.adjustedNumberOfPeople = adjustedNumberOfPeople;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public int getAdjustedNumberOfPeople() {
    return adjustedNumberOfPeople;
  }

  @Override
  public String toString() {
    return "Meal{" +
        "recipe=" + recipe +
        ", adjustedNumberOfPeople=" + adjustedNumberOfPeople +
        '}';
  }
}
