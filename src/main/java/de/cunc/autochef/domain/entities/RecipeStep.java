package de.cunc.autochef.domain.entities;

import java.util.Objects;

public class RecipeStep {

  private int step;
  private String description;

  public RecipeStep(int step, String description) {
    if (step <= 0) {
      throw new IllegalArgumentException("step must be greater than 0");
    }

    description = description.strip();
    if (description.length() == 0) {
      throw new IllegalArgumentException("description must not be empty");
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecipeStep that = (RecipeStep) o;
    return step == that.step && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(step, description);
  }
}
