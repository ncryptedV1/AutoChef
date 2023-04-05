package de.cunc.autochef;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.util.io.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.util.io.UserOutputInterface;
import java.io.File;

public class AutoChef {

  public static void main(String[] args) {
    UserOutputInterface outputService = new ConsoleOutputService();

    outputService.info("Starte...");

    // initialize services
    RecipeRepository recipeRepository = new RecipeFileRepository(new File("recipes"));
    DialogService dialogService = new DialogService(recipeRepository);

    // start user dialog
    dialogService.startDialog();
    outputService.info("Dialog Endstatus: " + dialogService.getCurrentState());
  }
}