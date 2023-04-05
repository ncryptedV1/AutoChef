package de.cunc.autochef.domain.util.io;

import java.time.LocalDate;
import java.util.function.Function;

public interface UserInputParser {
  
  public Integer getInteger(Integer lowerBound, Integer upperBound, String question);
  
  public LocalDate getDate(LocalDate after, LocalDate before, String question);
  
  public String getString(Function<String, String> validator, String question);

}
