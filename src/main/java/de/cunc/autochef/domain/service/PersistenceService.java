package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entity.Recipe;
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

public class PersistenceService {

  private static final File persistenceFolder = new File("persistence");
  private static final File recipesFolder = new File(
      persistenceFolder.getPath() + File.separator + "recipes");

  public static void init() throws RuntimeException {
    try {
      if (recipesFolder.mkdirs()) {
        ConsoleOutputService.info(
            "Rezept-Persistenz-Ordner angelegt in '" + recipesFolder.getAbsolutePath() + "'");
      } else if (!recipesFolder.exists()) {
        ConsoleOutputService.severe(
            "Rezept-Persistenz-Ordner in '" + recipesFolder.getAbsolutePath()
                + "' konnte nicht angelegt werden!");
        throw new RuntimeException();
      }
    } catch (SecurityException ex) {
      ConsoleOutputService.severe("Es fehlen Berechtigungen den Persistenz-Ordner anzulegen unter '"
          + recipesFolder.getAbsolutePath() + "'.");
      throw ex;
    }
  }

  public static void saveRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.getId());
    try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(targetFile))) {
      stream.writeObject(recipe);
    } catch (IOException e) {
      ConsoleOutputService.severe(
          "Fehler während der Persistierung von Rezept " + recipe.getId() + ": " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public static boolean deleteRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.getId());
    if (targetFile.exists()) {
      try {
        Files.delete(targetFile.toPath());
      } catch (IOException e) {
        ConsoleOutputService.severe(
            "Fehler während der Löschung von Rezept " + recipe.getId() + ": " + e.getMessage());
        throw new RuntimeException(e);
      }
      return true;
    }
    return false;
  }

  public static List<Recipe> getRecipes() {
    try (Stream<Path> recipePaths = Files.list(recipesFolder.toPath())) {
      List<String> recipeIds = recipePaths.map(path -> path.getFileName().toString()).toList();
      return recipeIds.stream().map(PersistenceService::getRecipe).toList();
    } catch (IOException e) {
      ConsoleOutputService.severe(
          "Fehler während dem Abrufen der persistierten Rezepte: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public static Recipe getRecipe(String id) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + id);
    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(targetFile))) {
      return (Recipe) stream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      ConsoleOutputService.severe(
          "Fehler während dem Abrufen des persistierten Rezepts " + id + ": " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
