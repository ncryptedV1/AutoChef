package de.cunc.autochef.domain.util.io;

import java.time.LocalDate;
import java.util.function.Function;

public interface UserOutputInterface {
  
  public void info(String msg);

  public void warning(String msg);

  public void severe(String msg);

  public void rawOut(Object msg);

  public void rawErr(Object msg);
  
}
