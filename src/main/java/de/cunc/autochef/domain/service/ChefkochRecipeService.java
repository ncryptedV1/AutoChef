package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.GroceryList;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChefkochRecipeService {

  private static String getWebsiteBody(URL url) throws IOException {
    URLConnection connection = url.openConnection();
    InputStream inputStream = connection.getInputStream();
    String encoding = connection.getContentEncoding();  // ** WRONG: should use "con.getContentType()" instead but it returns something like "text/html; charset=UTF-8" so this value must be parsed to extract the actual encoding
    encoding = encoding == null ? "UTF-8" : encoding;
    StringBuffer content = new StringBuffer();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding))) {
      String curLine;
      while ((curLine = reader.readLine()) != null) {
        content.append(curLine);
      }
    }
    return content.toString();
  }

  public static Recipe getRecipe(String url) {
    try {
      String content = getWebsiteBody(new URL(url));
      String name = extractRecipeName(content);
      GroceryList groceryList = extractIngredients(content);
      List<RecipeStep> recipeSteps = extractRecipeSteps(content);
      return new Recipe(name, groceryList, recipeSteps);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Die angegebene URL weist ein ungültiges Format auf!");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Es ist ein Fehler beim Abruf der Website aufgetreten!");
    }
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
      Integer quantity;
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
    Matcher matcher = Pattern.compile("<div class=\"ds-box\">(.*?)</div>").matcher(content);
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
