package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {

  String name;
  List<RecipeStep> recipeStepList = new ArrayList<>();
  GroceryList ingredients;

  public Recipe(String name, RecipeStep... recipeStepList) {
    // todo: check if steps are unique in the recipeStepList
    this.name = name;
    Collections.addAll(this.recipeStepList, recipeStepList);

    this.aggregateIngredients();
  }

  private void aggregateIngredients() {
    // todo: implement
    // this should get all ingredients from all recipesteps and summarize them in
    // `ingredients`
  }

  public String toString() {
    // todo: implement
    return null;
  }
}
