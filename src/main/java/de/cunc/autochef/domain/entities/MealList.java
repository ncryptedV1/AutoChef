package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealList {

  List<Meal> meals = new ArrayList<>();

  public MealList(Meal... meals) {
    Collections.addAll(this.meals, meals);
  }

  @Override
  public String toString() {
    return "MealList{" +
        "meals=" + meals +
        '}';
  }
}
