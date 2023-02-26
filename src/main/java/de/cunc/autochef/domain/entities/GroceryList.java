package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroceryList {

  List<GroceryItem> entries = new ArrayList<>();

  public GroceryList() {
  }

  public GroceryList(GroceryItem... items) {
    Collections.addAll(this.entries, items);
  }

  public void addItem(GroceryItem entry) {
    this.entries.add(entry);
  }

  public String toString() {
    // todo: implement
    return null;
  }
}
