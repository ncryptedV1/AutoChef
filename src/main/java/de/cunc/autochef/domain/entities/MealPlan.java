package de.cunc.autochef.domain.entities;

import java.time.LocalDate;
import java.util.List;

public class MealPlan {

  List<Meal> mealList;
  GroceryList groceryList;
  LocalDate start;
  LocalDate end;

  public MealPlan(List<Meal> mealList, LocalDate start, LocalDate end) {
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

  @Override
  public String toString() {
    return "MealPlan{" +
        "mealList=" + mealList +
        ", groceryList=" + groceryList +
        ", start=" + start +
        ", end=" + end +
        '}';
  }
}
