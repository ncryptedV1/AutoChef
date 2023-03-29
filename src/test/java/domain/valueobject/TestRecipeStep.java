package domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test RecipeStep")
public class TestRecipeStep {

  private RecipeStep recipeStep;
  private int step;
  private String description;

  @BeforeEach
  public void setUp() {
    step = 5;
    description = "This is a random string";
    recipeStep = new RecipeStep(step, description);
  }

  @Test
  public void testConstructorHappyPath() {
    // arrange
    int s = 1;

    // act
    RecipeStep res = new RecipeStep(s, "desc");

    // assert
    assertNotNull(res);
  }

  @Test
  public void testConstructorException() {
    // arrange
    int step = -1;
    String desc = "xyz";

    // assert
    assertThrows(IllegalArgumentException.class, () -> new RecipeStep(step, desc));
  }

  @Test
  public void testConstructorException2() {
    // arrange
    int step = -1;

    // assert
    assertThrows(IllegalArgumentException.class, () -> new RecipeStep(step, " "));
  }

  @Test
  public void testGetStep() {
    // arrange 
    // act
    int res = recipeStep.getStep();
    // assert
    assertEquals(res, step);
  }

  @Test
  public void testGetDescription() {
    // arrange 
    // act
    String res = recipeStep.getDescription();
    // assert
    assertEquals(res, description);
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
  public void testEqualsDifferent() {
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
  public void testHashCodeFalse() {
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
