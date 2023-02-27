package de.cunc.autochef.domain.valueobjects;

public enum Unit {

  LITER("Liter", "l"),
  MILLILITER("Milliliter", "ml"),
  KILOGRAM("Kilogram", "kg"),
  GRAM("Gram", "g"),
  PIECE("Piece", "pc(s)"),
  TABLESPOON("Tablespoon", "tbsp"),
  TEASPOON("Teaspoon", "tsp");

  private String name;
  private String abbreviation;

  Unit(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  @Override
  public String toString() {
    return "Unit{" +
        "name='" + name + '\'' +
        ", abbreviation='" + abbreviation + '\'' +
        '}';
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}