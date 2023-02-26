package de.cunc.autochef.domain.entities;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;

public class GroceryItem {

  Ingredient ingredient;
  Quantity quantity;
  Unit unit;

  public GroceryItem(Ingredient ingredient, Quantity quantity, Unit unit) {
    this.ingredient = ingredient;
    this.quantity = quantity;
    this.unit = unit;
  }

  @Override
  public String toString() {
    return "GroceryItem{" +
        "ingredient=" + ingredient +
        ", quantity=" + quantity +
        ", unit=" + unit +
        '}';
  }
}
