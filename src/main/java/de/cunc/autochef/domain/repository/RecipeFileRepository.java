package de.cunc.autochef.domain.repository;

import de.cunc.autochef.domain.entity.Recipe;
import de.cunc.autochef.domain.util.io.ConsoleOutputService;
import de.cunc.autochef.domain.util.io.OutputService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class RecipeFileRepository implements RecipeRepository {

  private final File recipesFolder;
  OutputService outputService;

  public RecipeFileRepository(File recipesFolder) {
    this.recipesFolder = recipesFolder;
    outputService = new ConsoleOutputService();
    
    try {
      if (recipesFolder.mkdirs()) {
        outputService.info(
            "Rezept-Persistenz-Ordner angelegt in '" + recipesFolder.getAbsolutePath() + "'");
      } else if (!recipesFolder.exists()) {
        outputService.severe(
            "Rezept-Persistenz-Ordner in '" + recipesFolder.getAbsolutePath()
                + "' konnte nicht angelegt werden!");
        throw new RuntimeException();
      }
    } catch (SecurityException ex) {
      outputService.severe("Es fehlen Berechtigungen den Persistenz-Ordner anzulegen unter '"
          + recipesFolder.getAbsolutePath() + "'.");
      throw ex;
    }
  }

  public void saveRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.getId());
    try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(targetFile))) {
      stream.writeObject(recipe);
    } catch (IOException e) {
      outputService.severe(
          "Fehler während der Persistierung von Rezept " + recipe.getId() + ": " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public boolean deleteRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.getId());
    if (targetFile.exists()) {
      try {
        Files.delete(targetFile.toPath());
      } catch (IOException e) {
        outputService.severe(
            "Fehler während der Löschung von Rezept " + recipe.getId() + ": " + e.getMessage());
        throw new RuntimeException(e);
      }
      return true;
    }
    return false;
  }

  public List<Recipe> getRecipes() {
    try (Stream<Path> recipePaths = Files.list(recipesFolder.toPath())) {
      List<String> recipeIds = recipePaths.map(path -> path.getFileName().toString()).toList();
      return recipeIds.stream().map(this::getRecipe).toList();
    } catch (IOException e) {
      outputService.severe(
          "Fehler während dem Abrufen der persistierten Rezepte: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public Recipe getRecipe(String id) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + id);
    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(targetFile))) {
      return (Recipe) stream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      outputService.severe(
          "Fehler während dem Abrufen des persistierten Rezepts " + id + ": " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
