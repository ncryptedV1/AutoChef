package de.cunc.autochef.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

public class Quantity implements Serializable {

  private final double value;

  public Quantity(double number) {
    if (number < 0) {
      throw new IllegalArgumentException("Mengenangabe muss größer als 0 sein");
    }
    this.value = number;
  }

  public double getValue() {
    return this.value;
  }

  public Quantity multiply(double factor) {
    return new Quantity(value * factor);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Quantity quantity = (Quantity) o;
    return Double.compare(quantity.value, value) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
