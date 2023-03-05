package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.valueobjects.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Ingredient")
public class TestIngredient {

  @Test
  void testConstructorHappyPath() {
    // arrange
    String expected = "pineapple";

    // act
    Ingredient ingredient = new Ingredient(expected);
  
    // assert
    assertNotNull(ingredient);
  }

  @Test
  void testConstructorException() {
    // arrange
    String input = "   ";
    String input2 = "";

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Ingredient(input));
    assertThrows(IllegalArgumentException.class, () -> new Ingredient(input2));
  }

  @Test
  void testGetId() {
    // arrange
    String name = "banana";
    Ingredient ingredient = new Ingredient(name);
    String expected = name.toLowerCase();
    
    // act
    String res = ingredient.getId();

    // assert
    assertEquals(expected, res);
  }
  
  @Test
  void testGetValue() {
    // arrange
    String name = "banana";
    Ingredient ingredient = new Ingredient(name);
    
    // act
    String res = ingredient.getValue();
    
    // assert
    assertEquals(name, res);
  }
  
  @Test
  void testEqualsResSelf() {
    // arrange
    Ingredient ingredient1 = mock(Ingredient.class);
    
    // act
    boolean res = ingredient1.equals(ingredient1);
    
    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsResSame() {
    // arrange
    String value = "banana";
    Ingredient ingredient1 = new Ingredient(value);
    Ingredient ingredient2 = new Ingredient(value);

    // act
    boolean res = ingredient1.equals(ingredient2);

    // assert
    assertTrue(res);
  }
  
  @Test
  void testEqualsDifferent() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("nutella");
    
    // act
    boolean res = ingredient1.equals(ingredient2);
    
    // assert
    assertFalse(res);
  }

  @Test
  void testEqualsNull() {
    // arrange
    Ingredient ingredient1 = mock(Ingredient.class);

    // act
    boolean res = ingredient1.equals(null);

    // assert
    assertFalse(res);
  }
  
  @Test
  void testHashCodeTrue() {
    // arrange
    String value = "banana";
    Ingredient ingredient1 = new Ingredient(value);
    Ingredient ingredient2 = new Ingredient(value);
    
    // act
    int code1 = ingredient1.hashCode();
    int code2 = ingredient2.hashCode();
    
    // assert
    assertEquals(code1, code2);
  }

  @Test
  void testHashCodeFalse() {
    // arrange
    Ingredient ingredient1 = new Ingredient("banana");
    Ingredient ingredient2 = new Ingredient("nutella");

    // act
    int code1 = ingredient1.hashCode();
    int code2 = ingredient2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }
  
}
