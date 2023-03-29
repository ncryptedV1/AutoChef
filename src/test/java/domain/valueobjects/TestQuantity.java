package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobject.Quantity;
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

  @Test
  void testEqualsResSelf() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(q1);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsResSame() {
    // arrange
    int value = 77;
    Quantity q1 = new Quantity(value);
    Quantity q2 = new Quantity(value);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsDifferent() {
    // arrange
    Quantity q1 = new Quantity(993);
    Quantity q2 = new Quantity(29);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertFalse(res);
  }

  @Test
  void testEqualsNull() {
    // arrange
    Quantity q1 = new Quantity(8);

    // act
    boolean res = q1.equals(null);

    // assert
    assertFalse(res);
  }

  @Test
  void testHashCodeTrue() {
    // arrange
    int value = 4;
    Quantity q1 = new Quantity(value);
    Quantity q2 = new Quantity(value);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  void testHashCodeFalse() {
    // arrange
    Quantity q1 = new Quantity(3);
    Quantity q2 = new Quantity(2);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }

}
