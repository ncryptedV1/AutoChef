package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.cunc.autochef.domain.valueobjects.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Quantity")
public class TestQuantity {

  Quantity quantity;
  @BeforeEach
  void init() {
  }
  
  @Test
  void testGetValue() {
    // arrange
    double actual = 2;
    quantity = new Quantity(actual);
    
    // act
    double res = quantity.getValue();
    
    // assert
    assertEquals(res, actual);
  }
  
}
