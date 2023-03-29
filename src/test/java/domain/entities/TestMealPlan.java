package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.aggregate.Meal;
import de.cunc.autochef.domain.aggregate.MealPlan;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.Unit;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test MealPlan")
public class TestMealPlan {

  MealPlan mealPlan;

  List<Meal> meals;

  LocalDate start;

  LocalDate end;

  @BeforeEach
  void init() {
    start = LocalDate.now();
    end = start.plusDays(5);
    int totalMeals = start.until(end).getDays();

    meals = new ArrayList<>();
    for (int i = 0; i < totalMeals; i++) {
      meals.add(mock(Meal.class));
    }

    mealPlan = new MealPlan(meals, start, end);
  }

  @Test
  void testConstructorHappyPath() {
    // arrange
    int days = 5;
    LocalDate s = LocalDate.now();
    LocalDate e = s.plusDays(days);
    int totalMeals = s.until(e).getDays();

    List<Meal> m = new ArrayList<>();
    for (int i = 0; i < totalMeals; i++) {
      m.add(mock(Meal.class));
    }

    // act
    MealPlan res = new MealPlan(m, s, e);

    // assert
    assertNotNull(res);
  }

  @Test
  void testConstructorException() {
    // arrange
    int days = 3;
    LocalDate s = LocalDate.now();
    LocalDate e = s.plusDays(days);
    int totalMeals = s.until(e).getDays();

    List<Meal> m = new ArrayList<>();
    for (int i = 0; i < totalMeals - 1; i++) {
      m.add(mock(Meal.class));
    }

    // act
    // assert
    assertThrows(IllegalArgumentException.class, () -> new MealPlan(m, s, e));
  }


  @Test
  void testGetStart() {
    // arrange
    // act
    LocalDate res = mealPlan.getStart();

    // assert
    assertEquals(start, res);
  }

  @Test
  void testGetEnd() {
    // arrange
    // act
    LocalDate res = mealPlan.getEnd();

    // assert
    assertEquals(end, res);
  }

  @Test
  void testGetDays() {
    // arrange
    int d = 28;
    LocalDate s = LocalDate.now();
    LocalDate e = s.plusDays(d);
    List<Meal> m = new ArrayList<>();
    for (int i = 0; i < d; i++) {
      m.add(mock(Meal.class));
    }

    MealPlan mealTest = new MealPlan(m, s, e);

    // act
    int res = mealTest.getDays();

    // assert
    assertEquals(d, res);
  }


  @Test
  void testGetGroceryList() {
    // arrange
    int d = 3;
    LocalDate s = LocalDate.now();
    LocalDate e = s.plusDays(d);
    List<Meal> m = new ArrayList<>();
    for (int i = 0; i < d; i++) {
      m.add(generateMeal());
    }

    MealPlan mealPlanTest = new MealPlan(m, s, e);

    // expected
    GroceryList expected = new GroceryList();
    List<GroceryItem> items = generateMeal().getGroceryList().getItems();
    items.forEach(g -> expected.addItem(
        new GroceryItem(g.getIngredient(), g.getQuantity().multiply(d), g.getUnit())));

    // act
    GroceryList res = mealPlanTest.getGroceryList();

    // assert
    String s1 = expected.getItems().stream().map(GroceryItem::toString).sorted().toList()
        .toString();
    String s2 = res.getItems().stream().map(GroceryItem::toString).sorted().toList().toString();
    assertEquals(s1, s2);
  }

  Meal generateMeal() {
    GroceryList groceries = new GroceryList();
    groceries.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(2), new Unit("piece")));
    groceries.addItem(
        new GroceryItem(new Ingredient("banana"), new Quantity(1), new Unit("kilogram")));
    groceries.addItem(
        new GroceryItem(new Ingredient("apple juice"), new Quantity(1), new Unit("liter")));
    groceries.addItem(
        new GroceryItem(new Ingredient("orange"), new Quantity(3), new Unit("slice")));

    Recipe recipe = new Recipe("any value", groceries, mock(List.class));

    return new Meal(recipe, 2);
  }

}
