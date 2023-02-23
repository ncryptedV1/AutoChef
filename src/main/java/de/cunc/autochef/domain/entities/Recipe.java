package de.cunc.autochef.domain.entities;

import java.util.List;

public class Recipe {

    String name;
    List<RecipeStep> recipeStepList;
    GroceryList ingredients;

    public Recipe(String name, List<RecipeStep> recipeStepList, GroceryList ingredients) {
        this.name = name;
        this.recipeStepList = recipeStepList;
        this.ingredients = ingredients;
    }
}
