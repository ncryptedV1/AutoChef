package de.cunc.autochef.domain.aggregate;

import de.cunc.autochef.domain.util.Formats;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.Unit;
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
    this.start = start;
    this.end = end;
    
    int days = getDays();
    if (meals.size() != days) {
      throw new IllegalArgumentException(
          "Mahlzeiten-Plan spannt " + days + " Tage, es wurden allerdings nur " + meals.size()
              + " Mahlzeiten übergeben");
    }

    this.meals = meals;
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

  public GroceryList getGroceryList() {
    Map<IngredientUnitTuple, Double> itemGroups = meals.stream()
        .flatMap(meal -> meal.getGroceryList().getItems().stream()).collect(Collectors.groupingBy(
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
        + "Gesamt:\n"
        + getGroceryList().toString();
  }

  private record IngredientUnitTuple(Ingredient ingredient, Unit unit) {

  }
}
