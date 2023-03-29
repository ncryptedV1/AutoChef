package domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("Test Recipe")
public class TestRecipe {

  private Recipe recipe;
  private String name;
  private GroceryList groceryList;
  private List<RecipeStep> recipeStepList;

  @BeforeEach
  public void setUp() {
    name = "banana split";
    groceryList = mock(GroceryList.class);

    RecipeStep step1 = new RecipeStep(1, "any value");
    recipeStepList = new ArrayList<>();
    recipeStepList.add(step1);

    recipe = new Recipe(name, groceryList, recipeStepList);
  }

  @Test
  @Order(1)
  public void testConstructorHappyPath() {
    // arrange
    String n = "any string";
    GroceryList g = mock(GroceryList.class);
    List<RecipeStep> r = mock(List.class);

    // act
    Recipe res = new Recipe(n, g, r);

    // assert
    assertNotNull(res);
  }

  @Test
  @Order(2)
  public void testConstructorHappyPath2() {
    // arrange
    String val = "custom value";
    String n = "any string";
    GroceryList g = mock(GroceryList.class);
    RecipeStep step1 = new RecipeStep(1, val);
    RecipeStep step2 = new RecipeStep(2, val);

    // act
    Recipe res = new Recipe(n, g, step1, step2);

    // assert
    assertNotNull(res);
  }

  @Test
  public void testConstructorException() {
    // arrange
    String val = "custom value";
    GroceryList g = mock(GroceryList.class);
    RecipeStep step1 = new RecipeStep(1, val);
    RecipeStep step2 = new RecipeStep(1, val);

    List<RecipeStep> r = new ArrayList<>();
    r.add(step1);
    r.add(step2);

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Recipe(val, g, r));
  }


  @Test
  public void testConstructorException2() {
    // arrange
    String val = "custom value";
    GroceryList g = mock(GroceryList.class);
    RecipeStep step1 = new RecipeStep(1, val);
    RecipeStep step2 = new RecipeStep(3, val);

    List<RecipeStep> r = new ArrayList<>();
    r.add(step1);
    r.add(step2);

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Recipe(val, g, r));
  }

  @Test
  public void testConstructorException3() {
    // arrange
    String val = "custom value";
    GroceryList g = mock(GroceryList.class);
    RecipeStep step1 = new RecipeStep(1, val);
    RecipeStep step2 = new RecipeStep(2, val);

    List<RecipeStep> r = new ArrayList<>();
    r.add(step1);
    r.add(step2);

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Recipe(" ", g, r));
  }

  @Test
  public void testGetName() {
    // arrange 
    // act
    String res = recipe.getName();
    // assert
    assertEquals(res, name);
  }

  @Test
  public void testGetRecipeSteps() {
    // arrange 
    // act
    List<RecipeStep> res = recipe.getRecipeSteps();
    // assert
    assertEquals(res, recipeStepList);
  }

  @Test
  public void testGetGroceryList() {
    // arrange 
    // act
    GroceryList res = recipe.getGroceryList();
    // assert
    assertEquals(res, groceryList);
  }

  @Test
  public void testGetId() {
    // arrange 
    String actual = name.toLowerCase();
    // act
    String res = recipe.getId();
    // assert
    assertEquals(res, actual);
  }

  @Test
  public void testEqualsResSelf() {
    // arrange
    // act
    boolean res = recipe.equals(recipe);

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsResSame() {
    // arrange
    // act
    boolean res = recipe.equals(generateCompatibleRecipe());

    // assert
    assertTrue(res);
  }

  @Test
  public void testEqualsDifferent() {
    // arrange
    // act
    boolean res = recipe.equals(generateIncompatibleRecipe());

    // assert
    assertFalse(res);
  }

  @Test
  public void testEqualsNull() {
    // arrange
    // act
    boolean res = recipe.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  public void testHashCodeTrue() {
    // arrange
    // act
    int code1 = recipe.hashCode();
    int code2 = generateCompatibleRecipe().hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  public void testHashCodeFalse() {
    // arrange
    // act
    int code1 = recipe.hashCode();
    int code2 = generateIncompatibleRecipe().hashCode();

    // assert
    assertNotEquals(code1, code2);
  }

  private Recipe generateIncompatibleRecipe() {
    String val = "apple juice";
    GroceryList g = mock(GroceryList.class);
    List<RecipeStep> r = new ArrayList<>();
    return new Recipe(val, g, r);
  }

  private Recipe generateCompatibleRecipe() {
    GroceryList g = mock(GroceryList.class);
    List<RecipeStep> r = new ArrayList<>();
    return new Recipe(name, g, r);
  }

}
