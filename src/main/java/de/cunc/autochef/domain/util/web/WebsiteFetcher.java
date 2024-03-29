package de.cunc.autochef.domain.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebsiteFetcher {

  public static String getWebsiteBody(String urlString) {
    try {
      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      InputStream inputStream = connection.getInputStream();
      String encoding = connection.getContentEncoding();
      encoding = encoding == null ? "UTF-8" : encoding;
      StringBuilder content = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(inputStream, encoding))) {
        String curLine;
        while ((curLine = reader.readLine()) != null) {
          content.append(curLine);
        }
      }
      return content.toString();
    } catch (
        MalformedURLException e) {
      throw new IllegalArgumentException("Die angegebene URL weist ein ungültiges Format auf!");
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Es ist ein Fehler beim Abruf der Website aufgetreten: " + e.getMessage());
    }
  }

}
