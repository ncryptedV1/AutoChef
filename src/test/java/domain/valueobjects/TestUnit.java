package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cunc.autochef.domain.valueobjects.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("Test Unit")
public class TestUnit {
  @Test
  void testConstructorHappyPath() {
    // arrange
    String expected = "gram";

    // act
    Unit unit = new Unit(expected);

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
    String expected = "piece";
    Unit unit = new Unit(expected);
    
    // act
    String res = unit.getValue();
    
    // assert
    assertEquals(expected, res);
  }
  
  
  @Test
  void testToString() {
    // arrange
    String expected = "piece";
    Unit unit = new Unit(expected);

    // act
    String res = unit.toString();

    // assert
    assertEquals(expected, res);
  }
  
}
