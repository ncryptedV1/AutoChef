package domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import org.junit.jupiter.api.DisplayName;
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
  void testGetId() {
    // arrange
    String val = "Gram";
    Unit unit = new Unit(val);
    String expected = val.toLowerCase();

    // act
    String res = unit.getId();

    // assert
    assertEquals(expected, res);
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
    String value = "piece";
    Unit q1 = new Unit(value);
    Unit q2 = new Unit(value);

    // act
    boolean res = q1.equals(q2);

    // assert
    assertTrue(res);
  }

  @Test
  void testEqualsDifferent() {
    // arrange
    Unit q1 = new Unit("gram");
    Unit q2 = new Unit("liter");

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
    String value = "piece";
    Unit q1 = new Unit(value);
    Unit q2 = new Unit(value);

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertEquals(code1, code2);
  }

  @Test
  void testHashCodeFalse() {
    // arrange
    Unit q1 = new Unit("gram");
    Unit q2 = new Unit("liter");

    // act
    int code1 = q1.hashCode();
    int code2 = q2.hashCode();

    // assert
    assertNotEquals(code1, code2);
  }


}
