package domain.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.aggregate.Meal;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.Unit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("Test Meal")
public class TestMeal {

  private Meal meal;
  private int people;

  @BeforeEach
  public void setUp() {
    people = 5;
    String val = "test";
    Recipe recipe = new Recipe(val, generateGroceryList(), mock(List.class));
    meal = new Meal(recipe, people);
  }

  @Test
  public void testConstructorHappyPath() {
    // arrange
    int p = 5;
    Recipe r = mock(Recipe.class);

    // act
    Meal res = new Meal(r, p);

    // assert
    assertNotNull(res);
  }

  @Test
  @Order(1)
  public void testGetRecipe() {
    // arrange
    Recipe actual = mock(Recipe.class);
    Meal mealTest = new Meal(actual, 4);

    // act
    Recipe res = mealTest.getRecipe();

    // assert
    assertEquals(res, actual);
  }

  @Test
  @Order(2)
  public void testSetRecipe() {
    // arrange
    Recipe actual = mock(Recipe.class);
    // act
    meal.setRecipe(actual);
    // assert
    Recipe res = meal.getRecipe();
    assertEquals(res, actual);
  }

  @Test
  public void testGetRecipeSteps() {
    // arrange 
    // act
    int res = meal.getAdjustedNumberOfPeople();

    // assert
    assertEquals(res, people);
  }

  @Test
  @Order(3)
  public void testGetGroceryList() {
    // arrange
    GroceryList g = new GroceryList();
    g.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(2 * people), new Unit("piece")));
    g.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(people), new Unit("kilogram")));
    g.addItem(new GroceryItem(new Ingredient("apple juice"), new Quantity(people),
        new Unit("liter")));
    g.addItem(
        new GroceryItem(new Ingredient("orange"), new Quantity(3 * people), new Unit("slice")));

    // act
    GroceryList res = meal.getGroceryList();

    // assert
    String expected = g.toString();
    String actual = res.toString();
    assertEquals(expected, actual);
  }

  @Test
  public void testEqualsResSelf() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(q1);

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsResSame() {
    // arrange
    Recipe recipe1 = new Recipe("banana split", mock(GroceryList.class), mock(List.class));
    Meal q1 = new Meal(recipe1, 2);
    Meal q2 = new Meal(recipe1, 2);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsDifferent() {
    // arrange
    Recipe recipe1 = new Recipe("banana split", mock(GroceryList.class), mock(List.class));
    Meal q1 = new Meal(recipe1, 2);
    Meal q2 = new Meal(recipe1, 5);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertFalse(res);
  }

  @Test
  public void testEqualsNull() {
    // arrange
    Recipe recipe1 = new Recipe("banana split", mock(GroceryList.class), mock(List.class));
    Meal q1 = new Meal(recipe1, 2);

    // act
    boolean res = q1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  public void testHashCodeTrue() {
    // arrange
    Recipe recipe1 = new Recipe("banana split", mock(GroceryList.class), mock(List.class));
    Meal q1 = new Meal(recipe1, 2);
    Meal q2 = new Meal(recipe1, 2);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  public void testHashCodeFalse() {
    // arrange
    Recipe recipe1 = new Recipe("banana split", mock(GroceryList.class), mock(List.class));
    Meal q1 = new Meal(recipe1, 2);
    Meal q2 = new Meal(recipe1, 4);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }

  private GroceryList generateGroceryList() {
    GroceryList groceries = new GroceryList();
    groceries.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(2), new Unit("piece")));
    groceries.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(1), new Unit("kilogram")));
    groceries.addItem(
        new GroceryItem(new Ingredient("apple juice"), new Quantity(1), new Unit("liter")));
    groceries.addItem(
        new GroceryItem(new Ingredient("orange"), new Quantity(3), new Unit("slice")));

    return groceries;
  }
}
