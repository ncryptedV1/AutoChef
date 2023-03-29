package de.cunc.autochef.domain.repository;

import de.cunc.autochef.domain.entity.Recipe;
import java.util.List;

public interface RecipeRepository {

  void saveRecipe(Recipe recipe);

  boolean deleteRecipe(Recipe recipe);

  List<Recipe> getRecipes();

  Recipe getRecipe(String id);
}
