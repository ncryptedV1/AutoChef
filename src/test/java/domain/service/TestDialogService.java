package domain.service;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.util.io.InputParser;
import de.cunc.autochef.domain.util.io.OutputService;
import domain.util.ConsoleInputParserFake;
import domain.util.ConsoleOutputReaderFake;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test DialogService")
public class TestDialogService {

  DialogService dialogService;
  
  @BeforeEach
  public void setUpClass() {
    File persistenceFolder = new File("recipes-test");
    RecipeRepository recipeFileRepository = new RecipeFileRepository(persistenceFolder);
    OutputService outputService = new ConsoleOutputReaderFake();
    InputParser inputParser = new ConsoleInputParserFake();
    
    dialogService = new DialogService(recipeFileRepository, outputService, inputParser);
  }
  
  @Test
  void testStartMain() {
    // arrange
    // act
    dialogService.startMain();
    // assert
  }
}
