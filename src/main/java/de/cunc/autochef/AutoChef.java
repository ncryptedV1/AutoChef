package de.cunc.autochef;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.io.ConsoleInputReader;
import de.cunc.autochef.domain.service.io.ConsoleOutputService;
import de.cunc.autochef.domain.service.io.DialogInputParser;
import de.cunc.autochef.domain.service.io.InputParser;
import de.cunc.autochef.domain.service.io.InputReader;
import de.cunc.autochef.domain.service.io.OutputService;
import java.io.File;

public class AutoChef {

  public static void main(String[] args) {
    OutputService outputService = new ConsoleOutputService("AutoChef");
    InputReader inputReader = new ConsoleInputReader();
    InputParser inputParser = new DialogInputParser(inputReader, outputService);

    outputService.info("Starte...");

    // initialize services
    RecipeRepository recipeRepository = new RecipeFileRepository(new File("recipes"),
        outputService);
    DialogService dialogService = new DialogService(recipeRepository, outputService, inputParser);

    // start user dialog
    dialogService.startDialog();
    outputService.info("Dialog Endstatus: " + dialogService.getCurrentState());
  }
}