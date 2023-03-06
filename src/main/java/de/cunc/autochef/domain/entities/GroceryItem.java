package de.cunc.autochef.domain.entities;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.io.Serializable;
import java.util.Objects;

public class GroceryItem implements Serializable {

  private Ingredient ingredient;
  private Quantity quantity;
  private Unit unit;

  public GroceryItem(Ingredient ingredient, Quantity quantity, Unit unit) {
    this.ingredient = ingredient;
    this.quantity = quantity;
    this.unit = unit;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public Quantity getQuantity() {
    return quantity;
  }

  public Unit getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return quantity.toString() + unit.toString() + " " + ingredient.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroceryItem that = (GroceryItem) o;
    return Objects.equals(ingredient, that.ingredient) && Objects.equals(quantity, that.quantity)
        && Objects.equals(unit, that.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ingredient, quantity, unit);
  }
}
