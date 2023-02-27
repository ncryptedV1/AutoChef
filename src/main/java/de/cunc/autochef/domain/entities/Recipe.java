package de.cunc.autochef.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Recipe implements Serializable {

  private String name;
  private List<RecipeStep> recipeStepList = new ArrayList<>();
  private GroceryList ingredients;

  public Recipe(String name, List<RecipeStep> recipeStepList) {
    recipeStepList.sort((step1, step2) -> step1.getStep() > step2.getStep() ? 1 : -1);
    for (int i = 0; i < recipeStepList.size(); i++) {
      if (recipeStepList.get(i).getStep() != i + 1) {
        throw new IllegalArgumentException(
            "recipe step " + (i + 1) + " is not included - no consecutive recipe supplied");
      }
    }

    this.recipeStepList = recipeStepList;
    this.name = name;

    this.aggregateIngredients();
  }

  public Recipe(String name, RecipeStep... recipeStepList) {
    this(name, Arrays.asList(recipeStepList));
  }

  private void aggregateIngredients() {
    List<GroceryItem> groceryItems = this.recipeStepList.stream()
        .flatMap(step -> step.getIngredients().getItems().stream()).toList();
    this.ingredients = new GroceryList(groceryItems);
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
    return Objects.equals(name, recipe.name) && Objects.equals(recipeStepList,
        recipe.recipeStepList) && Objects.equals(ingredients, recipe.ingredients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, recipeStepList, ingredients);
  }
}
