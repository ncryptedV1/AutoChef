package de.cunc.autochef.domain.util.io;

import de.cunc.autochef.AutoChef;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConsoleOutputService implements OutputService {

  private final Logger logger;

  static {
    try (InputStream inputStream = AutoChef.class.getResourceAsStream("/logging.properties")) {
      LogManager.getLogManager().readConfiguration(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ConsoleOutputService(String name) {
    logger = Logger.getLogger(name);
  }

  public void info(String msg) {
    logger.info(msg);
  }

  public void warning(String msg) {
    logger.warning(msg);
  }

  public void severe(String msg) {
    logger.severe(msg);
  }

  public void rawOut(Object msg) {
    System.out.println(msg);
  }

  public void rawErr(Object msg) {
    System.err.println(msg);
  }
}
