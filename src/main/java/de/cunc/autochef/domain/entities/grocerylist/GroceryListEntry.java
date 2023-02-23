package de.cunc.autochef.domain.entities.grocerylist;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;

public class GroceryListEntry {

  Ingredient ingredient;
  Quantity quantity;
  Unit unit;

  public GroceryListEntry(Ingredient ingredient, Quantity quantity, Unit unit) {
    this.ingredient = ingredient;
    this.quantity = quantity;
    this.unit = unit;
  }
}
