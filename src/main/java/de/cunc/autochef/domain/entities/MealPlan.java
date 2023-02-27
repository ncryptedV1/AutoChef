package de.cunc.autochef.domain.entities;

import java.time.LocalDate;
import java.util.List;

public class MealPlan {

  private List<Meal> mealList;
  private GroceryList groceryList;
  private LocalDate start;
  private LocalDate end;

  public MealPlan(List<Meal> mealList, LocalDate start, LocalDate end) {
    this.mealList = mealList;
    this.aggregateGroceryLists();
    this.start = start;
    this.end = end;
  }

  private void aggregateGroceryLists() {
    List<GroceryItem> groceryItems = this.mealList.stream()
        .flatMap(meal -> meal.getRecipe().getIngredients().getItems().stream()).toList();
    this.groceryList = new GroceryList(groceryItems);
  }

  public List<Meal> getMealList() {
    return mealList;
  }

  public GroceryList getGroceryList() {
    return groceryList;
  }

  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }

  @Override
  public String toString() {
    return "MealPlan{" + "mealList=" + mealList + ", groceryList=" + groceryList + ", start="
        + start + ", end=" + end + '}';
  }
}
