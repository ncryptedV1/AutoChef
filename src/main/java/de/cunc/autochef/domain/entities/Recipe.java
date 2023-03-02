package de.cunc.autochef.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Recipe {

  private String name;
  private GroceryList ingredients;
  private List<RecipeStep> recipeStepList = new ArrayList<>();

  public Recipe(String name, GroceryList ingredients, List<RecipeStep> recipeStepList) {
    recipeStepList.sort((step1, step2) -> step1.getStep() > step2.getStep() ? 1 : -1);
    for (int i = 0; i < recipeStepList.size(); i++) {
      if (recipeStepList.get(i).getStep() != i + 1) {
        throw new IllegalArgumentException(
            "Recipe step " + (i + 1) + " is not included - no consecutive recipe supplied");
      }
    }

    this.name = name.strip();
    this.ingredients = ingredients;
    this.recipeStepList = recipeStepList;
  }

  public Recipe(String name, GroceryList ingredients, RecipeStep... recipeStepList) {
    this(name, ingredients, Arrays.asList(recipeStepList));
  }

  public String getName() {
    return name;
  }

  public List<RecipeStep> getRecipeStepList() {
    return recipeStepList;
  }

  public GroceryList getIngredients() {
    return ingredients;
  }

  public String getId() {
    return this.name.toLowerCase();
  }

  @Override
  public String toString() {
    return "Recipe{" + "name='" + name + '\'' + ", recipeStepList=" + recipeStepList
        + ", ingredients=" + ingredients + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return Objects.equals(getId(), recipe.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
