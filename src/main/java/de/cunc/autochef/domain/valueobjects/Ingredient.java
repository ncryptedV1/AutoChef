package de.cunc.autochef.domain.valueobjects;

import java.util.Objects;

public class Ingredient {

  private final String name;

  public Ingredient(String name) {
    name = name.strip();
    if (name.length() == 0) {
      throw new IllegalArgumentException("name must not be empty");
    }

    this.name = name;
  }

  public String getValue() {
    return this.name;
  }

  @Override
  public String toString() {
    return name;
  }

  public String getId() {
    return this.name.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ingredient that = (Ingredient) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
