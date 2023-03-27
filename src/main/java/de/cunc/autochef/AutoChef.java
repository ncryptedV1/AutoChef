package de.cunc.autochef;

import de.cunc.autochef.domain.service.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.PersistenceService;

public class AutoChef {

  public static void main(String[] args) {
    ConsoleOutputService.info("Starte...");
    try {
      PersistenceService.init();
    } catch (RuntimeException ex) {
      System.exit(1);
    }
    DialogService.startDialog();
    ConsoleOutputService.info("Dialog Endstatus: " + DialogService.getCurrentState());
  }
}