package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import de.cunc.autochef.domain.valueobject.Ingredient;
import de.cunc.autochef.domain.valueobject.Quantity;
import de.cunc.autochef.domain.valueobject.RecipeStep;
import de.cunc.autochef.domain.valueobject.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChefkochRecipeFetcher {

  public static Recipe getRecipe(String url) {
    String content = WebsiteFetcher.getWebsiteBody(url);
    String name = extractRecipeName(content);
    GroceryList groceryList = extractIngredients(content);
    List<RecipeStep> recipeSteps = extractRecipeSteps(content);
    return new Recipe(name, groceryList, recipeSteps);
  }

  private static String extractRecipeName(String content) {
    Matcher matcher = Pattern.compile("<h1 class=\"\">(.*)</h1>")
        .matcher(content);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      throw new IllegalArgumentException("Es konnte kein Rezeptname gefunden werden!");
    }
  }

  private static GroceryList extractIngredients(String content) {
    Matcher matcher = Pattern.compile(
            "<tr>\s*<td width=\"33%\" class=\"td-left \"><span>([^<]*)</span></td>\s*<td width=\"66%\" class=\"td-right\">\s*<span>(<a[^>]*>)?([^<]*)(</a>)?</span>\s*</td>\s*</tr>")
        .matcher(content);
    List<GroceryItem> items = new ArrayList<>();
    while (matcher.find()) {
      String amount = matcher.group(1).strip().replaceAll("\s+", " ");
      String[] amountSplit = amount.split(" ");
      int quantity;
      String unit;
      if (amountSplit[0].matches("\\d+")) {
        quantity = Integer.parseInt(amountSplit[0]);
        if (amountSplit.length > 1) {
          unit = amount.substring(amountSplit[0].length() + 1);
        } else {
          unit = "Stk.";
        }
      } else {
        quantity = 1;
        unit = amount;
      }
      String ingredient = matcher.group(3).strip().replaceAll("\s+", " ");

      items.add(
          new GroceryItem(new Ingredient(ingredient), new Quantity(quantity), new Unit(unit)));
    }
    if (items.isEmpty()) {
      throw new IllegalArgumentException("Es konnten keine Zutaten gefunden werden!");
    }
    return new GroceryList(items);
  }

  private static List<RecipeStep> extractRecipeSteps(String content) {
    Matcher matcher = Pattern.compile("Zubereitung</h2>.*?<div class=\"ds-box\">(.*?)</div>")
        .matcher(content);
    List<RecipeStep> steps = new ArrayList<>();
    int idx = 1;
    if (matcher.find()) {
      String allStepsString = matcher.group(1);
      String[] stepStrings = allStepsString.replaceAll("(\s*<br>\s*)+", "###").split("###");
      for (String stepString : stepStrings) {
        steps.add(new RecipeStep(idx++, stepString));
      }
    }
    if (steps.isEmpty()) {
      throw new IllegalArgumentException(
          "Es konnten keine Zubereitungs-Anweisungen gefunden werden!");
    }
    return steps;
  }
}
