package de.cunc.autochef.domain.valueobjects;

public final class Quantity {

  private final double value;

  public Quantity(double number) {
    if (number < 0) {
      throw new IllegalArgumentException("number must not be less than 0");
    }
    this.value = number;
  }

  public double getValue() {
    return this.value;
  }
}
