package domain.util;

import de.cunc.autochef.domain.util.io.InputParser;
import java.time.LocalDate;
import java.util.function.Function;

public class InputParserFake implements InputParser {

  @Override
  public Integer getInteger(Integer lowerBound, Integer upperBound, String question) {
    return 0;
  }

  @Override
  public LocalDate getDate(LocalDate after, LocalDate before, String question) {
    return LocalDate.now();
  }

  @Override
  public String getString(Function<String, String> validator, String question) {
    return "any string";
  }
}
