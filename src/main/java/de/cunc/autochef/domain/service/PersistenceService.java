package de.cunc.autochef.domain.service;

import com.google.gson.Gson;
import de.cunc.autochef.domain.entities.Recipe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
      ConsoleOutputService.info(
          "Rezept-Persistenz-Ordner angelegt an " + recipesFolder.getAbsolutePath());
    } catch (SecurityException ex) {
      ConsoleOutputService.severe("Es fehlen Berechtigungen den Persistenz-Ordner anzulegen unter '"
          + recipesFolder.getAbsolutePath() + "'.");
      throw ex;
    }
  }

  public static void saveRecipe(Recipe recipe) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + recipe.getId() + ".json");
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(targetFile))) {
      writer.write(new Gson().toJson(recipe));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Recipe> getRecipes() {
    try (Stream<Path> recipePaths = Files.list(recipesFolder.toPath())) {
      List<String> recipeIds = recipePaths.map(path -> path.getFileName().toString())
          .map(name -> name.substring(0, name.length() - 5)).toList();
      return recipeIds.stream().map(PersistenceService::getRecipe).toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Recipe getRecipe(String id) {
    File targetFile = new File(recipesFolder.getPath() + File.separator + id + ".json");
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(targetFile))) {
      return new Gson().fromJson(reader, Recipe.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
