package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.GroceryList;
import de.cunc.autochef.domain.entities.Meal;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("Test Meal")
public class TestMeal {

  Meal meal;
  
  Recipe recipe;
  int people;
  
  @BeforeEach
  void init() {
    people = 5;
    recipe = new Recipe(" ", generateGroceryList(), mock(List.class));
    recipe = new Recipe(" ", generateGroceryList(), mock(List.class));
    meal = new Meal(recipe, people); 
  }
  
  @Test
  void testConstructorHappyPath() {
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
  void testGetRecipe() {
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
  void testSetRecipe() {
    // arrange
    Recipe actual = mock(Recipe.class);
    // act
    meal.setRecipe(actual);
    // assert
    Recipe res = meal.getRecipe();
    assertEquals(res, actual);
  }

  @Test
  void testGetRecipeSteps() {
    // arrange 
    // act
    int res = meal.getAdjustedNumberOfPeople();
    
    // assert
    assertEquals(res, people);
  }

  @Test
  @Order(3)
  void testGetGroceryList() {
    // arrange
    GroceryList g = new GroceryList();
    g.addItem(new GroceryItem(new Ingredient("banana"), new Quantity(2*people), new Unit("piece")));
    g.addItem(new GroceryItem(new Ingredient("banana"), new Quantity(1*people), new Unit("kilogram")));
    g.addItem(new GroceryItem(new Ingredient("apple juice"), new Quantity(1*people), new Unit("liter")));
    g.addItem(new GroceryItem(new Ingredient("orange"), new Quantity(3*people), new Unit("slice")));
    
    // act
    GroceryList res = meal.getGroceryList();
    
    // assert
    String expected = g.toString();
    String actual = res.toString();
    assertEquals(expected, actual);
  }
  
  GroceryList generateGroceryList() {
    GroceryList groceries = new GroceryList();
    groceries.addItem(new GroceryItem(new Ingredient("banana"), new Quantity(2), new Unit("piece")));
    groceries.addItem(new GroceryItem(new Ingredient("banana"), new Quantity(1), new Unit("kilogram")));
    groceries.addItem(new GroceryItem(new Ingredient("apple juice"), new Quantity(1), new Unit("liter")));
    groceries.addItem(new GroceryItem(new Ingredient("orange"), new Quantity(3), new Unit("slice")));
    
    return groceries;
  }
}
