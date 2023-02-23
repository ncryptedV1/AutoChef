package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    List<GroceryListEntry> entries;

    public GroceryList() {
        this.entries = new ArrayList<GroceryListEntry>();
    }

    void addItem(GroceryListEntry entry) {
        this.entries.add(entry);
    }
}
