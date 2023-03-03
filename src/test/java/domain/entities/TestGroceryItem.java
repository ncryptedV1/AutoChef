package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Grocery Item")
public class TestGroceryItem {

  GroceryItem item;
  Ingredient ingredient;
  Quantity quantity;
  Unit unit;
      
  @BeforeEach
  void init() {
    ingredient = new Ingredient("banana");
    quantity = new Quantity(2);
    unit = Unit.PIECE;
    
    item = new GroceryItem(ingredient, quantity, unit); 
  }
  
  
  @Test
  void testGetIngredient() {
    // arrange 
    // act
    Ingredient ing = item.getIngredient();
    // assert
    assertEquals(ingredient, ing);
  }

  @Test
  void testGetQuantity() {
    // arrange 
    // act
    Quantity q = item.getQuantity();
    // assert
    assertEquals(quantity, q);
  }
  
  @Test
  void testGetUnit() {
    // arrange 
    // act
    Unit u = item.getUnit();
    // assert
    assertEquals(unit, u);
  }

  @Test
  void testToString() {
    // arrange
    String actual = "GroceryItem{" +
        "ingredient=" + ingredient +
        ", quantity=" + quantity +
        ", unit=" + unit +
        '}';

    // act
    String res = item.toString();

    // assert
    assertEquals(res, actual);
  }

}
