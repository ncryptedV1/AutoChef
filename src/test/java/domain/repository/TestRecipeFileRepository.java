package domain.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test RecipeFileRepository")
public class TestRecipeFileRepository {

  private static Recipe testRecipe;
  private static RecipeFileRepository recipeFileRepository;

  @BeforeAll
  public static void setUp() {
    GroceryList list = mock(GroceryList.class);
    RecipeStep step = new RecipeStep(1, "description");
    testRecipe = new Recipe("test123", list, step);

    recipeFileRepository = new RecipeFileRepository(new File("recipes"));
  }

  @AfterAll
  public static void tearDown() {
    recipeFileRepository.deleteRecipe(testRecipe);
  }

  @Test
  @DisplayName("Save a recipe")
  public void testSaveAndGetRecipe() {
    // arrange
    // act
    // assert
    assertDoesNotThrow(() -> recipeFileRepository.saveRecipe(testRecipe));
  }

  @Test
  @DisplayName("Get a list of recipes")
  public void testGetRecipes() {
    // arrange
    recipeFileRepository.saveRecipe(testRecipe);

    // act
    List<Recipe> recipes = recipeFileRepository.getRecipes();

    // assert
    assertTrue(recipes.contains(testRecipe));

  }

  @Test
  @DisplayName("Get specific recipe")
  public void testGetRecipe() {
    // arrange
    recipeFileRepository.saveRecipe(testRecipe);

    // act
    Recipe retrievedRecipe = recipeFileRepository.getRecipe(testRecipe.getId());

    // assert
    assertEquals(testRecipe, retrievedRecipe);
  }

}
