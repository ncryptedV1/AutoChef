package domain.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import domain.util.OutputServiceFake;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test RecipeFileRepository")
public class TestRecipeFileRepository {

  private Recipe recipe;
  private static File persistenceFolder;
  private static RecipeFileRepository recipeFileRepository;

  @BeforeAll
  public static void setUpClass() {
    persistenceFolder = new File("recipes-test");
    recipeFileRepository = new RecipeFileRepository(persistenceFolder,
        new OutputServiceFake());
  }

  @AfterAll
  public static void tearDownClass() {
    for (File file : persistenceFolder.listFiles()) {
      file.delete();
    }
    persistenceFolder.delete();
  }

  @BeforeEach
  public void setUp() {
    GroceryList list = mock(GroceryList.class);
    RecipeStep step = new RecipeStep(1, "description");
    recipe = new Recipe("test123", list, step);
  }

  @Test
  @DisplayName("Save a recipe")
  public void testSaveAndGetRecipe() {
    // arrange
    // act
    // assert
    assertDoesNotThrow(() -> recipeFileRepository.saveRecipe(recipe));
  }

  @Test
  @DisplayName("Get a list of recipes")
  public void testGetRecipes() {
    // arrange
    recipeFileRepository.saveRecipe(recipe);

    // act
    List<Recipe> recipes = recipeFileRepository.getRecipes();

    // assert
    assertTrue(recipes.contains(recipe));

  }

  @Test
  @DisplayName("Get specific recipe")
  public void testGetRecipe() {
    // arrange
    recipeFileRepository.saveRecipe(recipe);

    // act
    Recipe retrievedRecipe = recipeFileRepository.getRecipe(recipe.getId());

    // assert
    assertEquals(recipe, retrievedRecipe);
  }

}
