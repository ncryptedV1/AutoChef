package de.cunc.autochef.domain.entities;

import java.util.List;

public class Recipe {

    String name;
    List<RecipeStep> recipeStepList;
    GroceryList groceryList;

    public Recipe(String name, List<RecipeStep> recipeStepList, GroceryList groceryList) {
        this.name = name;
        this.recipeStepList = recipeStepList;
        this.groceryList = groceryList;
    }
}
