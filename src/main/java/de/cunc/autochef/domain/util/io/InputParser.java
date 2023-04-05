package de.cunc.autochef.domain.util.io;

import java.time.LocalDate;
import java.util.function.Function;

public interface InputParser {
  
  Integer getInteger(Integer lowerBound, Integer upperBound, String question);
  
  LocalDate getDate(LocalDate after, LocalDate before, String question);
  
  String getString(Function<String, String> validator, String question);

}
