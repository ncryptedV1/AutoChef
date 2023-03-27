package de.cunc.autochef;

import de.cunc.autochef.domain.service.ConsoleOutputService;
import de.cunc.autochef.domain.service.DialogService;

public class AutoChef {

  public static void main(String[] args) {
    ConsoleOutputService.info("Starte...");
    DialogService.startDialog();
    ConsoleOutputService.info("Dialog Endstatus: " + DialogService.getCurrentState());
  }
}