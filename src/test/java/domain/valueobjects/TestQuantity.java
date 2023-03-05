package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cunc.autochef.domain.valueobjects.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Quantity")
public class TestQuantity {

  @Test
  void testConstructorHappyPath() {
    // arrange
    double actual = 7;

    // act
    Quantity quantity = new Quantity(actual);

    // assert
    assertNotNull(quantity);
  }

  @Test
  void testConstructorException() {
    // arrange
    double input = -1;

    // assert
    assertThrows(IllegalArgumentException.class, () -> new Quantity(input));
  }
  
  @Test
  void testGetValue() {
    // arrange
    double expected = 2;
    Quantity quantity = new Quantity(expected);
    
    // act
    double res = quantity.getValue();
    
    // assert
    assertEquals(expected, res);
  }
  
  @Test
  void testToString() {
    // arrange
    double expected = 3;
    Quantity quantity = new Quantity(expected);
    
    // act
    String res = quantity.toString();
    
    // assert
    assertEquals(expected + "", res);
  }
  
  @Test
  void testMultiply() {
    // arrange
    double input = 4;
    double factor = 5;
    Quantity quantity = new Quantity(input);
    Quantity expected = new Quantity(input * factor);
    
    // act
    Quantity res = quantity.multiply(factor);
    
    // assert
    assertEquals(expected.getValue(), res.getValue());
  }
  
}
