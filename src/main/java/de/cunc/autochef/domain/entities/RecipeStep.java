package de.cunc.autochef.domain.entities;

public class RecipeStep {

  private int step;
  private String description;

  public RecipeStep(int step, String description) {
    if (step <= 0) {
      throw new IllegalArgumentException("step must be greater than 0");
    }

    this.step = step;
    this.description = description;
  }

  public int getStep() {
    return step;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return step + ". " + description;
  }
}
