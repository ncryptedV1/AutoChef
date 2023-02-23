package de.cunc.autochef.domain.entities;

import java.util.List;

public class MealList {

    List<Meal> meals;

    public MealList(Meal... meals) {
        for (Meal meal : meals) {
            this.meals.add(meal);
        }
    }
}
