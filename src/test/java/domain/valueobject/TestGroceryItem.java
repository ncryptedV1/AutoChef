package domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test GroceryItem")
public class TestGroceryItem {

  private GroceryItem item;
  private Ingredient ingredient;
  private Quantity quantity;
  private Unit unit;

  @BeforeEach
  public void setUp() {
    ingredient = new Ingredient("banana");
    quantity = new Quantity(2);
    unit = new Unit("piece");

    item = new GroceryItem(ingredient, quantity, unit);
  }

  @Test
  public void testConstructor() {
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
  public void testGetIngredient() {
    // arrange 
    // act
    Ingredient res = item.getIngredient();
    // assert
    assertEquals(res, ingredient);
  }

  @Test
  public void testGetQuantity() {
    // arrange 
    // act
    Quantity res = item.getQuantity();
    // assert
    assertEquals(res, quantity);
  }

  @Test
  public void testGetUnit() {
    // arrange 
    // act
    Unit res = item.getUnit();
    // assert
    assertEquals(res, unit);
  }


  @Test
  public void testEqualsResSelf() {
    // arrange
    Ingredient ingredient1 = new Ingredient("apple");
    Quantity quantity1 = new Quantity(44);
    Unit unit1 = new Unit("piece");

    GroceryItem q1 = new GroceryItem(ingredient1, quantity1, unit1);

    // act
    boolean res = q1.equals(q1);

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsResSame() {
    // arrange
    Ingredient ingredient1 = new Ingredient("apple");
    Quantity quantity1 = new Quantity(44);
    Unit unit1 = new Unit("piece");

    GroceryItem q1 = new GroceryItem(ingredient1, quantity1, unit1);
    GroceryItem q2 = new GroceryItem(ingredient1, quantity1, unit1);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsDifferent() {
    // arrange
    GroceryItem q1 = new GroceryItem(new Ingredient("apple"), new Quantity(2), new Unit("piece"));
    GroceryItem q2 = new GroceryItem(new Ingredient("banana"), new Quantity(2), new Unit("piece"));

    // act
    boolean res = q1.equals(q2);

    // assert
    assertFalse(res);
  }

  @Test
  public void testEqualsNull() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  public void testHashCodeTrue() {
    // arrange
    Ingredient ingredient1 = new Ingredient("apple");
    Quantity quantity1 = new Quantity(44);
    Unit unit1 = new Unit("piece");

    GroceryItem q1 = new GroceryItem(ingredient1, quantity1, unit1);
    GroceryItem q2 = new GroceryItem(ingredient1, quantity1, unit1);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  public void testHashCodeFalse() {
    // arrange
    GroceryItem q1 = new GroceryItem(new Ingredient("apple"), new Quantity(2), new Unit("piece"));
    GroceryItem q2 = new GroceryItem(new Ingredient("banana"), new Quantity(2), new Unit("piece"));

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }

}
