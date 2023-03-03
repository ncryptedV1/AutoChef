package de.cunc.autochef.domain.valueobjects;

public class Unit {

  private final String value;

  public Unit(String value) {
    value = value.strip();
    if (value.length() == 0) {
      throw new IllegalArgumentException(
          "Supplied identifier must be at least one character long!");
    }
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}