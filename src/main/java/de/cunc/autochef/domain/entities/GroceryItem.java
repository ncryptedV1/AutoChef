package de.cunc.autochef.domain.entities;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;

public class GroceryItem {

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
    return "GroceryItem{" +
        "ingredient=" + ingredient +
        ", quantity=" + quantity +
        ", unit=" + unit +
        '}';
  }
}
