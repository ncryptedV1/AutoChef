package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobjects.RecipeStep;
import de.cunc.autochef.domain.valueobjects.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Test RecipeStep")
public class TestRecipeStep {

  RecipeStep recipeStep;
  int step;
  String description;
      
  @BeforeEach
  void init() {
    step = 5;
    description = "This is a random string";
    recipeStep = new RecipeStep(step, description); 
  }
  
  @Test
  void testConstructorHappyPath() {
    // arrange
    int s = 1;
    
    // act
    RecipeStep res = new RecipeStep(s, "desc");
    
    // assert
    assertNotNull(res);
  }

  @Test
  void testConstructorException() {
    // arrange
    int step = -1;
    String desc = Mockito.anyString();

    // assert
    assertThrows(IllegalArgumentException.class, () -> new RecipeStep(step, desc));
  }

  @Test
  void testConstructorException2() {
    // arrange
    int step = -1;

    // assert
    assertThrows(IllegalArgumentException.class, () -> new RecipeStep(step, " "));
  }
  
  @Test
  void testGetStep() {
    // arrange 
    // act
    int res = recipeStep.getStep();
    // assert
    assertEquals(res, step);
  }

  @Test
  void testGetDescription() {
    // arrange 
    // act
    String res = recipeStep.getDescription();
    // assert
    assertEquals(res, description);
  }

  @Test
  void testEqualsResSelf() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(q1);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsResSame() {
    // arrange
    String val = "any string";
    int s = 1;
    RecipeStep q1 = new RecipeStep(s, val);
    RecipeStep q2 = new RecipeStep(s, val);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsDifferent() {
    // arrange
    String val = "any string";
    RecipeStep q1 = new RecipeStep(1, val);
    RecipeStep q2 = new RecipeStep(2, val);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertFalse(res);
  }

  @Test
  void testEqualsNull() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  void testHashCodeTrue() {
    // arrange
    String val = "any string";
    int s = 1;
    RecipeStep q1 = new RecipeStep(s, val);
    RecipeStep q2 = new RecipeStep(s, val);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  void testHashCodeFalse() {
    // arrange
    String val = "any string";
    RecipeStep q1 = new RecipeStep(1, val);
    RecipeStep q2 = new RecipeStep(2, val);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }
}
