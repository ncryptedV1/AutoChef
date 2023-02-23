package de.cunc.autochef.domain.entities;

import java.time.LocalDate;

public class MealPlan {

    MealList mealList;
    GroceryList groceryList;
    LocalDate start;
    LocalDate end;

    public MealPlan(MealList mealList, LocalDate start, LocalDate end) {
        this.mealList = mealList;
        this.aggregateGroceryLists();
        this.start = start;
        this.end = end;
    }

    private void aggregateGroceryLists() {
        // todo: implement
        // this should get all ingredients from all meals and summarize them in
        // `groceryList`
    }

    public String toString() {
        // todo: implement
        return "this is a mealPlan stringified";
    }
}
