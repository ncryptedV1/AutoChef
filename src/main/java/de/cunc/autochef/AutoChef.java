package de.cunc.autochef;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;
import java.io.File;

public class AutoChef {

  public static void main(String[] args) {
    ConsoleOutputService.info("Starte...");

    // initialize services
    RecipeRepository recipeRepository = new RecipeFileRepository(new File("recipes"));
    DialogService dialogService = new DialogService(recipeRepository);

    // start user dialog
    dialogService.startDialog();
    ConsoleOutputService.info("Dialog Endstatus: " + dialogService.getCurrentState());
  }
}