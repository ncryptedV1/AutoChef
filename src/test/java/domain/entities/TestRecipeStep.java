package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cunc.autochef.domain.entities.RecipeStep;
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
    String desc = Mockito.anyString();
    
    // act
    RecipeStep res = new RecipeStep(s, desc);
    
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
  void testToString() {
    // arrange
    String actual = step + ". " + description;

    // act
    String res = recipeStep.toString();

    // assert
    assertEquals(res, actual);
  }

}
