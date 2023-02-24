package de.cunc.autochef.domain.valueobjects;

public enum Unit {

    LITER("Liter", "l"),
    MILLILITER("Milliliter", "ml"),
    KILOGRAM("Kilogram", "kg"),
    GRAM("Gram", "g"),
    PIECE("Piece", "piece(s)"),
    TABLESPOON("Tablespoon", "tbsp"),
    TEESPOON("Teespoon", "tsp");

    private String name;
    private String abbreviation;

    Unit(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String abbreviated() {
        return this.abbreviation;
    }

}