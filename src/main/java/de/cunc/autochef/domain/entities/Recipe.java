package de.cunc.autochef.domain.entities;

import java.util.List;

public class Recipe {

    String name;
    List<RecipeStep> recipeStepList;
    GroceryList ingredients;

    public Recipe(String name, List<RecipeStep> recipeStepList) {
        // todo: check if steps are unique in the recipeStepList
        this.name = name;
        this.recipeStepList = recipeStepList;
        this.aggregateIngredients();
    }

    private void aggregateIngredients() {
        // todo: implement
        // this should get all ingredients from all recipesteps and summarize them in
        // `ingredients`
    }
}
