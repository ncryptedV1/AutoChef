package de.cunc.autochef.domain.valueobjects;

public class Ingredient {

  final String name;

  public Ingredient(String name) {
    if (name.length() == 0) {
      throw new IllegalArgumentException("name must not be empty");
    }

    this.name = name;
  }

  public String getValue() {
    return this.name;
  }
}
