package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroceryList {

  private List<GroceryItem> items = new ArrayList<>();

  public GroceryList(List<GroceryItem> items) {
    this.items = items;
  }

  public GroceryList(GroceryItem... items) {
    this(Arrays.asList(items));
  }

  public void addItem(GroceryItem entry) {
    this.items.add(entry);
  }

  public List<GroceryItem> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "GroceryList{" +
        "entries=" + items +
        '}';
  }
}
