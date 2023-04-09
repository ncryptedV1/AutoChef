package domain.repository;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import java.util.ArrayList;
import java.util.List;

public class RecipeFileRepositoryFake implements RecipeRepository {

  @Override
  public void saveRecipe(Recipe recipe) {
    
  }

  @Override
  public boolean deleteRecipe(Recipe recipe) {
    return false;
  }

  @Override
  public List<Recipe> getRecipes() {
    List<Recipe> recipes = new ArrayList<>();
    
    recipes.add(getRecipe("any"));
    recipes.add(getRecipe("any"));
    recipes.add(getRecipe("any"));
    recipes.add(getRecipe("any"));
    recipes.add(getRecipe("any"));
    
    return recipes;
  }

  @Override
  public Recipe getRecipe(String id) {
    List<RecipeStep> steps = new ArrayList<>();
    Recipe recipe = new Recipe("Rezept 1", new GroceryList(), steps);
    return recipe;
  }
}
