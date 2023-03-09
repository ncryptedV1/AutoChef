package domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.entities.GroceryList;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.service.PersistenceService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test PersistenceService")
public class TestPersistenceService {

  private Recipe testRecipe;
  String testId;

  @BeforeEach
  void setUp() {
    testId = "test123";
    GroceryList list = mock(GroceryList.class);
    RecipeStep step = new RecipeStep(1, "description");
    testRecipe = new Recipe(testId, list, step);
  }

  @Test
  @DisplayName("Save a recipe")
  void testSaveAndGetRecipe() {
    // arrange
    // act
    // assert
    assertDoesNotThrow(() -> PersistenceService.saveRecipe(testRecipe));
  }

  @Test
  @DisplayName("Get a list of recipes")
  void testGetRecipes() {
    // arrange
    PersistenceService.saveRecipe(testRecipe);
    
    // act
    List<Recipe> recipes = PersistenceService.getRecipes();

    // assert
    assertTrue(recipes.contains(testRecipe));
    
  }

  @Test
  @DisplayName("Get specific recipe")
  void testGetRecipe() {
    // arrange
    PersistenceService.saveRecipe(testRecipe);
    
    // act
    Recipe retrievedRecipe = PersistenceService.getRecipe(testId);

    // assert
    assertEquals(testRecipe, retrievedRecipe);

  }
  
}
