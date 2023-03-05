package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    unit = new Unit("piece");
    
    item = new GroceryItem(ingredient, quantity, unit); 
  }
  
  @Test
  void testConstructor() {
    // arrange
    Ingredient i = new Ingredient("banana");
    Quantity q = new Quantity(2);
    Unit u = new Unit("piece");

    // act
    GroceryItem res = new GroceryItem(i, q, u);
    
    // assert
    assertNotNull(res);
  }
  
  @Test
  void testGetIngredient() {
    // arrange 
    // act
    Ingredient res = item.getIngredient();
    // assert
    assertEquals(res, ingredient);
  }

  @Test
  void testGetQuantity() {
    // arrange 
    // act
    Quantity res = item.getQuantity();
    // assert
    assertEquals(res, quantity);
  }
  
  @Test
  void testGetUnit() {
    // arrange 
    // act
    Unit res = item.getUnit();
    // assert
    assertEquals(res, unit);
  }

  @Test
  void testToString() {
    // arrange
    String actual = quantity.toString() + unit.toString() + " " + ingredient.toString();

    // act
    String res = item.toString();

    // assert
    assertEquals(res, actual);
  }

}
