package de.cunc.autochef.domain.entities;

import java.time.LocalDate;

public class MealPlan {

    MealList mealList;
    GroceryList groceryList;
    LocalDate start;
    LocalDate end;

    public MealPlan(MealList mealList, GroceryList groceryList, LocalDate start, LocalDate end) {
        this.mealList = mealList;
        this.groceryList = groceryList;
        this.start = start;
        this.end = end;
    }
}
