package de.cunc.autochef.domain.entities;

import de.cunc.autochef.domain.utils.Formats;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MealPlan {

  private List<Meal> meals;
  private LocalDate start;
  private LocalDate end;

  public MealPlan(List<Meal> meals, LocalDate start, LocalDate end) {
    int days = start.until(end).getDays();
    if (meals.size() != days) {
      throw new IllegalArgumentException(
          "The meal plan covers " + days + " days, but the meal list contains " + meals.size());
    }

    this.meals = meals;
    this.start = start;
    this.end = end;
  }

  public List<Meal> getMeals() {
    return meals;
  }

  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public int getDays() {
    return start.until(getEnd()).getDays();
  }

  public GroceryList getIngredients() {
    Map<IngredientUnitTuple, Double> itemGroups = meals.stream()
        .flatMap(meal -> meal.getIngredients().getItems().stream()).collect(Collectors.groupingBy(
            item -> new IngredientUnitTuple(item.getIngredient(), item.getUnit()),
            Collectors.summingDouble(item -> item.getQuantity().getValue())));
    List<GroceryItem> items = itemGroups.entrySet().stream().map(
        entry -> new GroceryItem(entry.getKey().ingredient,
            new Quantity(entry.getValue()), entry.getKey().unit)).toList();
    return new GroceryList(items);
  }

  @Override
  public String toString() {
    return "Mahlzeiten-Plan von " + Formats.DATE_FORMAT.format(getStart()) + " - "
        + Formats.DATE_FORMAT.format(getEnd()) + ":\n"
        + IntStream.range(0, getDays())
        .mapToObj(day -> Formats.DATE_FORMAT.format(start.plusDays(day)) + ": " + meals.get(day)
            .toString())
        .collect(
            Collectors.joining("\n")) + "\n"
        + "\n"
        + "Gesamt-Einkaufsliste:\n"
        + getIngredients().toString();
  }

  private record IngredientUnitTuple(Ingredient ingredient, Unit unit) {

  }
}
