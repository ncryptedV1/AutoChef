package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Recipe {

  private String name;
  private GroceryList ingredients;
  private List<RecipeStep> recipeSteps = new ArrayList<>();

  public Recipe(String name, GroceryList ingredients, List<RecipeStep> recipeSteps) {
    recipeSteps.sort((step1, step2) -> step1.getStep() > step2.getStep() ? 1 : -1);
    for (int i = 0; i < recipeSteps.size(); i++) {
      if (recipeSteps.get(i).getStep() != i + 1) {
        throw new IllegalArgumentException(
            "Recipe step " + (i + 1) + " is not included - no consecutive recipe supplied");
      }
    }

    this.name = name.strip();
    this.ingredients = ingredients;
    this.recipeSteps = recipeSteps;
  }

  public Recipe(String name, GroceryList ingredients, RecipeStep... recipeSteps) {
    this(name, ingredients, Arrays.asList(recipeSteps));
  }

  public String getName() {
    return name;
  }

  public List<RecipeStep> getRecipeSteps() {
    return recipeSteps;
  }

  public GroceryList getIngredients() {
    return ingredients;
  }

  public String getId() {
    return this.name.toLowerCase();
  }

  @Override
  public String toString() {
    return "Rezept: " + name + ":\n"
        + ingredients.toString() + "\n"
        + "Zubereitung:\n"
        + recipeSteps.stream().map(step -> step.toString()).collect(Collectors.joining("\n"));
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
