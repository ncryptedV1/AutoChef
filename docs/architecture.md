- CLI
- Verwaltung von Essensgerichten für einen Haushalt
- Essenspläne und Einkaufslisten für einen bestimmten Zeitraum (z.B. die folgende Woche) einfach erstellen und generieren
lassen zu können
- Filterung und Adjustierung anhand verschiedener Faktoren 
  - favorisierte Zutaten (z.B. Reis, Nudeln)
  - Ernährungsweisen
- Speicherung von Gerichten, Rezepten und Zutaten
- eventuelle Abfrage von APIs wie etwa Chefkoch.de

# Value Objects
- Ingredient
- Quantity
- Unit

# Entities
- RecipeStep & Recipe
- Meal & MealPlan
- GroceryItem & GroceryList

# Domain Services
- bei komplexem Verhalten
  - Filtering
  - Output: Logging und Output-Formatierung
  - Input: User-Interaktion
  - Storage / Persistence
- Interface für (externen) Vertrag

# Funktionalität
- Generierung
  - Essensplan für Woche
  - Einkaufsliste
- Filterung
  - bestimmte Zutaten/Ernährungsweisen
  - Suche nach bestimmtem Rezept
- Output
  - Essensplan
  - Einkaufsliste
  - Rezepte mit Schritten
- Input
  - Zutat zu Einkaufsliste hinzufügen (manuell)
  - Mahlzeit / Rezept

![Architecture diagram](./res/architecture.svg)