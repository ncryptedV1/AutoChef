package de.cunc.autochef.domain.entity;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Recipe implements Serializable {

  private String name;
  private GroceryList groceryList;
  private List<RecipeStep> recipeSteps;

  public Recipe(String name, GroceryList groceryList, List<RecipeStep> recipeSteps) {
    recipeSteps.sort((step1, step2) -> step1.getStep() > step2.getStep() ? 1 : -1);
    for (int i = 0; i < recipeSteps.size(); i++) {
      if (recipeSteps.get(i).getStep() != i + 1) {
        throw new IllegalArgumentException(
            "Rezept-Schritt " + (i + 1)
                + " wurde nicht übergeben - kein zusammenhängendes Rezept konstruierbar");
      }
    }

    name = name.strip();
    if (name.length() == 0) {
      throw new IllegalArgumentException("Rezept-Name darf nicht leer sein");
    }

    this.name = name;
    this.groceryList = groceryList;
    this.recipeSteps = recipeSteps;
  }

  public Recipe(String name, GroceryList groceryList, RecipeStep... recipeSteps) {
    this(name, groceryList, new ArrayList<>(Arrays.asList(recipeSteps)));
  }

  public String getName() {
    return name;
  }

  public List<RecipeStep> getRecipeSteps() {
    return recipeSteps;
  }

  public GroceryList getGroceryList() {
    return groceryList;
  }

  public String getId() {
    return this.name.toLowerCase();
  }

  @Override
  public String toString() {
    return "Rezept: " + name + ":\n"
        + groceryList.toString() + "\n"
        + "Zubereitung:\n"
        + recipeSteps.stream().map(RecipeStep::toString).collect(Collectors.joining("\n"));
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
