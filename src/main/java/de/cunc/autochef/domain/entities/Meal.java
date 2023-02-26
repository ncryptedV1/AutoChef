package de.cunc.autochef.domain.entities;

public class Meal {

  Recipe recipe;
  int adjustedNumberOfPeople;

  public Meal(Recipe recipe, int adjustedNumberOfPeople) {
    this.recipe = recipe;
    this.adjustedNumberOfPeople = adjustedNumberOfPeople;
  }

  @Override
  public String toString() {
    return "Meal{" +
        "recipe=" + recipe +
        ", adjustedNumberOfPeople=" + adjustedNumberOfPeople +
        '}';
  }
}
