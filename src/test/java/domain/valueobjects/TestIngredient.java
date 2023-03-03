package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Ingredient")
public class TestIngredient {

  Ingredient ingredient;
  @BeforeEach
  void init() {
  }
  
  @Test
  void testGetValue() {
    // arrange
    String name = "banana";
    ingredient = new Ingredient(name);
    
    // act
    String res = ingredient.getValue();
    
    // assert
    assertEquals(res, name);
  }
  
  @Test
  void testEquals() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("banana");
    Ingredient ingredient3 = new Ingredient("nutella");
    
    // act
    boolean res1 = ingredient1.equals(ingredient2);
    boolean res2 = ingredient1.equals(ingredient3);
    
    // assert
    assertTrue(res1);
    assertFalse(res2);
  }
  
  @Test
  void testHashCode() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("banana");
    Ingredient ingredient3 = new Ingredient("nutella");
    
    // act
    boolean res1 = ingredient1.hashCode() == ingredient2.hashCode();
    boolean res2 = ingredient1.hashCode() == ingredient3.hashCode();
    
    // assert
    assertTrue(res1);
    assertFalse(res2);
  }
  
}
