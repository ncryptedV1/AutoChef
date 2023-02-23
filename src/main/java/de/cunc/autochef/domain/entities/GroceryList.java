package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    List<GroceryItem> entries;

    public GroceryList() {
        this.entries = new ArrayList<GroceryItem>();
    }

    public void addItem(GroceryItem entry) {
        this.entries.add(entry);
    }
}
