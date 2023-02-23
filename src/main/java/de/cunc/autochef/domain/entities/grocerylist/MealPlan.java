package de.cunc.autochef.domain.entities.grocerylist;

public class MealPlan {

    String name;
    GroceryList ingredients;

    public MealPlan(String name, GroceryList ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
}
