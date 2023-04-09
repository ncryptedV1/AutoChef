package domain.util;

import de.cunc.autochef.domain.service.io.InputParser;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Function;

public class InputParserFake implements InputParser {

  @Override
  public Integer getInteger(Integer lowerBound, Integer upperBound, String question) {
//    Random rand = new Random();

//    int n = rand.nextInt(lowerBound, upperBound + 1);

    return 4;
  }

  @Override
  public LocalDate getDate(LocalDate after, LocalDate before, String question) {
    return LocalDate.now();
  }

  @Override
  public String getString(Function<String, String> validator, String question) {
    return "https://www.chefkoch.de/rezepte/2424911382539696/Quinoabratlinge-mit-Knoblauch-Sauerrahm-Sauce.html";
//    return "22.2.2022";
  }
}
