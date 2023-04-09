package domain.service;

import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.io.DialogInputParser;
import de.cunc.autochef.domain.service.io.InputParser;
import de.cunc.autochef.domain.service.io.InputReader;
import de.cunc.autochef.domain.service.io.OutputService;
import domain.repository.RecipeFileRepositoryFake;
import domain.util.InputParserFake;
import domain.util.OutputServiceFake;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test DialogInputParser")
public class TestDialogInputParser {

  DialogInputParser inputParser;
  final int getIntegerRes = 38;
  final String getStringRes = "8";
  final LocalDate getDate = LocalDate.now();

  @BeforeEach
  public void setUpClass() {
    OutputService outputService = new OutputServiceFake();
    InputReader inputReader = new ConsoleInputReaderFake("" + getIntegerRes);

    inputParser = new DialogInputParser(inputReader, outputService);
  }

  @Test
  @DisplayName("get int from String")
  void testGetInteger() {
    // arrange
    String question = "any string";
    int lowerBound = 20;
    int upperBound = 40;
    
    // act
    int res = inputParser.getInteger(lowerBound, upperBound, question);
    
    // assert
    Assertions.assertEquals(getIntegerRes, res);
  }
}