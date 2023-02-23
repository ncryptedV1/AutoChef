package de.cunc.autochef.domain.entities;

import java.util.List;

public class Recipe {

    String name;
    List<RecipeStep> recipeStepList;
    GroceryList ingredients;

    public Recipe(String name, RecipeStep... recipeStepList) {
        // todo: check if steps are unique in the recipeStepList
        this.name = name;
        for (RecipeStep step : recipeStepList) {
            this.recipeStepList.add(step);
        }

        this.aggregateIngredients();
    }

    private void aggregateIngredients() {
        // todo: implement
        // this should get all ingredients from all recipesteps and summarize them in
        // `ingredients`
    }
}
