package domain.service;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.repository.RecipeFileRepository;
import de.cunc.autochef.domain.repository.RecipeRepository;
import de.cunc.autochef.domain.service.DialogService;
import de.cunc.autochef.domain.util.io.ConsoleOutputService;
import de.cunc.autochef.domain.util.io.InputParser;
import de.cunc.autochef.domain.util.io.OutputService;
import domain.repository.RecipeFileRepositoryFake;
import domain.util.InputParserFake;
import domain.util.OutputServiceFake;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Test DialogService")
public class TestDialogService {

  DialogService dialogService;
  
  @BeforeEach
  public void setUpClass() {
    OutputService outputService = new OutputServiceFake();
    InputParser inputParser = new InputParserFake();
    RecipeRepository recipeFileRepository = new RecipeFileRepositoryFake();

    dialogService = new DialogService(recipeFileRepository, outputService, inputParser);
  }
  
  @Test
  void testStartMain() {
    // arrange
//    DialogService mockService = mock(DialogService.class);
    // act
    dialogService.startMain();
    // assert
//    Mockito.verify(mockService, atLeast(0)).startMain();
  }
}