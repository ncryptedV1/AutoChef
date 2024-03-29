package de.cunc.autochef.domain.aggregate;

import de.cunc.autochef.domain.valueobject.GroceryItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryList implements Serializable {

  private List<GroceryItem> items;

  public GroceryList(List<GroceryItem> items) {
    this.items = items;
  }

  public GroceryList(GroceryItem... items) {
    this(new ArrayList<>(Arrays.asList(items)));
  }

  public void addItem(GroceryItem entry) {
    this.items.add(entry);
  }

  public List<GroceryItem> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "Zutaten:\n"
        + items.stream().map(item -> "- " + item.toString()).collect(Collectors.joining("\n"));
  }
}
