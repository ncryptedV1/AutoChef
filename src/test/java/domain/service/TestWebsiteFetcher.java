package domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cunc.autochef.domain.service.WebsiteFetcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test WebsiteFetcher")
public class TestWebsiteFetcher {

  @Test
  public void testGetWebsiteBody() {
    // arrange
    String validUrl = "https://google.com";

    // act
    String websiteBody = WebsiteFetcher.getWebsiteBody(validUrl);

    // assert
    Assertions.assertNotNull(websiteBody);
    Assertions.assertTrue(websiteBody.contains("<html"));
  }

  @Test
  public void testGetWebsiteBodyInvalidUrl() {
    // Given
    String invalidUrl = "any string";

    // When and Then
    assertThrows(IllegalArgumentException.class, () -> WebsiteFetcher.getWebsiteBody(invalidUrl));
  }

}
