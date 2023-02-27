package de.cunc.autochef.domain.valueobjects;

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
  public boolean equals(Object obj) {
    if (!(obj instanceof Ingredient)) {
      return false;
    }
    Ingredient compare = (Ingredient) obj;

    String transformed1 = this.name.trim().toLowerCase();
    String transformed2 = compare.name.trim().toLowerCase();

    return transformed1.equals(transformed2);
  }

  @Override
  public int hashCode() {
    String transformed = this.name.trim().toLowerCase();
    return transformed.hashCode();
  }
}
