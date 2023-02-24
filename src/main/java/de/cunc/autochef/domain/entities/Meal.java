package de.cunc.autochef.domain.entities;

public class Meal {

    Recipe recipe;
    int adjustedNumberOfPeople;

    public Meal(Recipe recipe, int adjustedNumberOfPeople) {
        this.recipe = recipe;
        this.adjustedNumberOfPeople = adjustedNumberOfPeople;
    }

    public String toString() {
        String res = String.format("%s for %d people", this.recipe.name, this.adjustedNumberOfPeople);
        return res;
    }
}
