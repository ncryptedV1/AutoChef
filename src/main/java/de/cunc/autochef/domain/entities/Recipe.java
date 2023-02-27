package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {

    private String name;
    private List<RecipeStep> recipeStepList = new ArrayList<>();
    private GroceryList ingredients;

    public Recipe(String name, RecipeStep... recipeStepList) {
        this.recipeStepList = Arrays.asList(recipeStepList);
        this.recipeStepList.sort((step1, step2) -> step1.getStep() > step2.getStep() ? 1 : -1);
        for (int i = 0; i < recipeStepList.length; i++) {
            if (recipeStepList[i].getStep() != i + 1) {
                throw new IllegalArgumentException(
                        "recipe step " + (i + 1) + " is not included - no consecutive recipe supplied");
            }
        }

        this.name = name;

        this.aggregateIngredients();
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
}
