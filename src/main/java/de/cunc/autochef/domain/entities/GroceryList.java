package de.cunc.autochef.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    List<GroceryItem> entries;

    public GroceryList() {
        this.entries = new ArrayList<GroceryItem>();
    }

    public GroceryList(GroceryItem... items) {
        this.entries = new ArrayList<GroceryItem>();
        for (GroceryItem item : items) {
            this.entries.add(item);
        }
    }

    public void addItem(GroceryItem entry) {
        this.entries.add(entry);
    }

    public String toString() {
        // todo: implement
        return null;
    }
}
