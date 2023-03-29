package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.service.ChefkochRecipeService;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import de.cunc.autochef.domain.valueobject.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test ChefkochRecipeService")
public class TestChefkochRecipeService {

  Recipe recipe;
  String url;
  /* 
  Rezept: Italienisches Schweinefilet à la Nonna:
  Zutaten:
- 500.0g Schweinefilet(s)
- 150.0g Parmaschinken, in dünnen Scheiben
- 1.0Stk. Zwiebel(n)
- 3.0Zehe/n Knoblauch
- 50.0g Oliven, schwarze
- 1.0Dose Tomaten, stückige
- 250.0ml Sahne
Zubereitung:
1. Schweinefilet in Medaillons schneiden, diese mit Parmaschinken umwickeln und in eine Auflaufform legen.
2. Zwiebeln und Knoblauch würfeln und in Olivenöl anschwitzen, aber keine Farbe nehmen lassen. Dosentomaten und Oliven dazugeben und langsam erhitzen, mit den Kräutern und Gewürzen abschmecken. Die Sahne dazugeben und aufkochen lassen. Evtl. nachwürzen. Es darf gerne auch scharf sein.
3. Die Soße auf die Schweinefiletscheiben geben und die Form für ungefähr 45 Minuten bei 180 Grad in den Ofen geben.
4. Dazu Reis oder Ciabatta reichen.

  * */

  @BeforeEach
  void setUp() {
    url = "https://www.chefkoch.de/rezepte/1909521311313294/Italienisches-Schweinefilet-a-la-Nonna.html";

    String title = "Italienisches Schweinefilet à la Nonna";
    GroceryItem i1 = new GroceryItem(new Ingredient("Schweinefilet(s)"), new Quantity(500),
        new Unit("g"));
    GroceryItem i2 = new GroceryItem(new Ingredient("Parmaschinken, in dünnen Scheiben"),
        new Quantity(150), new Unit("g"));
    GroceryItem i3 = new GroceryItem(new Ingredient("Zwiebel(n)"), new Quantity(1),
        new Unit("Stk."));
    GroceryItem i4 = new GroceryItem(new Ingredient("Knoblauch"), new Quantity(3),
        new Unit("Zehe/n"));
    GroceryItem i5 = new GroceryItem(new Ingredient("Oliven, schwarze"), new Quantity(50),
        new Unit("g"));
    GroceryItem i6 = new GroceryItem(new Ingredient("Tomaten, stückige"), new Quantity(1),
        new Unit("Dose"));
    GroceryItem i7 = new GroceryItem(new Ingredient("Sahne"), new Quantity(250), new Unit("ml"));

    GroceryList list = new GroceryList();
    list.addItem(i1);
    list.addItem(i2);
    list.addItem(i3);
    list.addItem(i4);
    list.addItem(i5);
    list.addItem(i6);
    list.addItem(i7);

    RecipeStep step1 = new RecipeStep(1,
        "Schweinefilet in Medaillons schneiden, diese mit Parmaschinken umwickeln und in eine Auflaufform legen.");
    RecipeStep step2 = new RecipeStep(2,
        "Zwiebeln und Knoblauch würfeln und in Olivenöl anschwitzen, aber keine Farbe nehmen lassen. Dosentomaten und Oliven dazugeben und langsam erhitzen, mit den Kräutern und Gewürzen abschmecken. Die Sahne dazugeben und aufkochen lassen. Evtl. nachwürzen. Es darf gerne auch scharf sein.");
    RecipeStep step3 = new RecipeStep(3,
        "Die Soße auf die Schweinefiletscheiben geben und die Form für ungefähr 45 Minuten bei 180 Grad in den Ofen geben.");
    RecipeStep step4 = new RecipeStep(4, "Dazu Reis oder Ciabatta reichen.");
    recipe = new Recipe(title, list, step1, step2, step3, step4);
  }

  @Test
  public void testGetRecipe() {
    // arrange
    // act
    Recipe res = ChefkochRecipeService.getRecipe(url);

    // assert
    assertEquals(recipe.getId(), res.getId());
    assertEquals(recipe.getGroceryList().toString(), res.getGroceryList().toString());
    assertEquals(recipe.getRecipeSteps(), res.getRecipeSteps());
  }
}
