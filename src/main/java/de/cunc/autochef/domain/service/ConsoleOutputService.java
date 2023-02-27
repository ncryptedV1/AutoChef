package de.cunc.autochef.domain.service;

import de.cunc.autochef.AutoChef;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConsoleOutputService {

  private static final Logger logger;

  static {
    try (InputStream inputStream = AutoChef.class.getResourceAsStream("/logging.properties")) {
      LogManager.getLogManager().readConfiguration(inputStream);
      logger = Logger.getLogger("AutoChef");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void info(String msg) {
    logger.info(msg);
  }

  public static void warning(String msg) {
    logger.warning(msg);
  }

  public static void severe(String msg) {
    logger.severe(msg);
  }

  public static void rawOut(Object msg) {
    System.out.println(msg);
  }

  public static void rawErr(Object msg) {
    System.err.println(msg);
  }
}
