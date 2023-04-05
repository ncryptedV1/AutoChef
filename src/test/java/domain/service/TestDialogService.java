package domain.service;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.service.io.InputParser;
import de.cunc.autochef.domain.service.io.OutputService;
import domain.util.InputParserFake;
import domain.util.OutputServiceFake;
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
    OutputService outputService = new OutputServiceFake();
    InputParser inputParser = new InputParserFake();
    RecipeRepository recipeFileRepository = new RecipeFileRepository(persistenceFolder, outputService);

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
