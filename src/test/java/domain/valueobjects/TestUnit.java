package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cunc.autochef.domain.valueobjects.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Unit")
public class TestUnit {
  @Test
  void testConstructorHappyPath() {
    // arrange
    String actual = "gram";

    // act
    Unit unit = new Unit(actual);

    // assert
    assertNotNull(unit);
  }
  
  @Test
  void testConstructorException() {
    // arrange
    String input = "";

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Unit(input));
  }
  
  
  @Test
  void testGetValue() {
    // arrange
    String actual = "piece";
    Unit unit = new Unit(actual);
    
    // act
    String res = unit.getValue();
    
    // assert
    assertEquals(res, actual);
  }
  
  
  @Test
  void testToString() {
    // arrange
    String actual = "piece";
    Unit unit = new Unit(actual);

    // act
    String res = unit.toString();

    // assert
    assertEquals(res, actual);
  }
  
}
