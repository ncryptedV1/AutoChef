- CLI
- Verwaltung von Essensgerichten für einen Haushalt
- Essenspläne und Einkaufslisten für einen bestimmten Zeitraum (z.B. die folgende Woche) einfach erstellen und generieren
lassen zu können. 
- Filterung und Adjustierung anhand verschiedener Faktoren 
  - favorisierte Zutaten (z.B. Reis, Nudeln)
  - Ernährungsweisen.
- Speicherung von Gerichten, Rezepte und Zutaten
- eventuelle Abfrage von APIs wie etwa Chefkoch.de


# Entities
- Meal / Recipe
- Grocerylist
  - ListEntry
- Mealplan (Woche, Monat etc.)

# Value Object
- Zutat
- Menge
- Einheit

# Domain Service
- bei komplexen Verhalten
  - Filtering
  - Output: output existing data in certain format
  - Input: add new stuff
  - Storage / Persistence
- Interface für (externen) Vertrag

# Aggregates
- Meal / Recipe / RecipeStep
- Grocerylist


# Functionality
- Generate
  - List from Recipe
- Filter/Adjust
  - List
  - Search for Recipe
- Output
  - List
  - Recipe
  - Recipe Step
- Input / Add
  - Item to list
  - Meal / Recipe

- 
