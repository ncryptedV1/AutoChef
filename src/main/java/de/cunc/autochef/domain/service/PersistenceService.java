package de.cunc.autochef.domain.service;

import de.cunc.autochef.domain.entities.Recipe;
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

  static {
    try {
      recipesFolder.mkdirs();
    } catch (SecurityException ex) {
      ConsoleOutputService.severe("Es fehlen Berechtigungen den Persistenz-Ordner anzulegen unter '"
          + recipesFolder.getAbsolutePath() + "'.");
      throw ex;
    }
  }

  public static void saveRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.hashCode());
    try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(targetFile))) {
      stream.writeObject(recipe);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Recipe> getRecipes() {
    try (Stream<Path> recipePaths = Files.list(recipesFolder.toPath())) {
      List<Integer> recipeIds = recipePaths.map(path -> path.getFileName().toString())
          .map(Integer::parseInt).toList();
      return recipeIds.stream().map(PersistenceService::getRecipe).toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Recipe getRecipe(int hashCode) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + hashCode);
    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(targetFile))) {
      return (Recipe) stream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
