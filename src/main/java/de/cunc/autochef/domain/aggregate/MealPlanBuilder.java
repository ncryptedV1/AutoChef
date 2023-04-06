package de.cunc.autochef.domain.aggregate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealPlanBuilder {

  private final Map<LocalDate, Meal> mealMap = new HashMap<>();
  private LocalDate startDate;
  private LocalDate endDate;

  public MealPlanBuilder setStartDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public MealPlanBuilder setEndDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public MealPlanBuilder addMeal(LocalDate date, Meal meal) {
    mealMap.put(date, meal);
    return this;
  }

  public MealPlan build() {
    if (startDate == null || endDate == null) {
      throw new IllegalStateException("Start- und Enddatum müssen gesetzt sein.");
    }
    if (mealMap.isEmpty()) {
      throw new IllegalStateException("Es müssen Mahlzeiten hinzugefügt werden.");
    }

    List<Meal> meals = new ArrayList<>();
    for (LocalDate date = startDate; !date.isEqual(endDate); date = date.plusDays(1)) {
      Meal meal = mealMap.get(date);
      if (meal == null) {
        throw new IllegalStateException("Es fehlt eine Mahlzeit für das Datum: " + date);
      }
      meals.add(meal);
    }

    return new MealPlan(meals, startDate, endDate);
  }
}
