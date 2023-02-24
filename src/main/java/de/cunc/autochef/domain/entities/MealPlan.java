package de.cunc.autochef.domain.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MealPlan {

    List<Meal> meals;
    GroceryList groceryList;
    LocalDate start;
    LocalDate end;

    public MealPlan(LocalDate start, LocalDate end, Meal... meals) {
        this.meals = Arrays.stream(meals).toList();
        this.aggregateGroceryLists();
        this.start = start;
        this.end = end;
    }

    public MealPlan(LocalDate start, LocalDate end, List<Meal> meals) {
        this.meals = meals;
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
        String res = "";

        for (Meal meal : meals) {
            res = meal.toString() + System.lineSeparator();
        }

        return res;
    }
}
