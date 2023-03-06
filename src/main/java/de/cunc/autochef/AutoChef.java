package de.cunc.autochef;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.Recipe;
import de.cunc.autochef.domain.entities.RecipeStep;
import de.cunc.autochef.domain.service.ChefkochRecipeService;
import de.cunc.autochef.domain.service.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.MockService;
import de.cunc.autochef.domain.service.PersistenceService;
import java.util.List;

public class AutoChef {

  public static void main(String[] args) {
    ConsoleOutputService.info("Starte...");

    // generate mock recipe and save for later usage
    List<GroceryItem> groceryItems = MockService.generateGroceryItems();
    List<RecipeStep> recipeSteps = MockService.generateRecipeSteps();
    Recipe recipe = MockService.generateRecipe(groceryItems, recipeSteps);
    PersistenceService.saveRecipe(recipe);

    // test chefkoch recipe service
    Recipe ckRecipe = ChefkochRecipeService.getRecipe(
        "https://www.chefkoch.de/rezepte/1012931206519137/Milchschnitte-oder-Maxiking-Schnitte.html");
    PersistenceService.saveRecipe(ckRecipe);

    // start actual user interaction
    DialogService.startDialog();

    ConsoleOutputService.info("Dialog Endstatus: " + DialogService.getCurrentState());
  }
}