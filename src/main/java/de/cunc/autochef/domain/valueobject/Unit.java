package de.cunc.autochef.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {

  private final String value;

  public Unit(String value) {
    value = value.strip();
    if (value.length() == 0) {
      throw new IllegalArgumentException(
          "Einheits-Wert darf nicht leer sein");
    }

    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public String getId() {
    return this.value.toLowerCase();
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
    Unit unit = (Unit) o;
    return Objects.equals(getId(), unit.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}