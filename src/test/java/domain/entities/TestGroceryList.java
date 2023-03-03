package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.GroceryList;
import de.cunc.autochef.domain.valueobjects.Ingredient;
import de.cunc.autochef.domain.valueobjects.Quantity;
import de.cunc.autochef.domain.valueobjects.Unit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Grocery List")
public class TestGroceryList {

  GroceryList list;
  GroceryItem item1;
  GroceryItem item2;
      
  @BeforeEach
  void init() {
  }
  
  
  @Test
  void testGetItems() {
    // arrange
    item1 = new GroceryItem(new Ingredient("banana"), new Quantity(2), Unit.PIECE);  
    item2 = new GroceryItem(new Ingredient("nutella"), new Quantity(200), Unit.GRAM);  
    list = new GroceryList(item1, item2);
    
    // act
    List<GroceryItem> items = list.getItems();
    
    // assert
    assertEquals(items.size(), 2);
  }
  
  
  @Test
  void testAddItem() {
    // arrange
    item1 = new GroceryItem(new Ingredient("banana"), new Quantity(2), Unit.PIECE);
    list = new GroceryList();
    
    // act
    list.addItem(item1);

    // assert
    List<GroceryItem> items = list.getItems();
    assertEquals(items.size(), 1);

    GroceryItem res = items.get(0);
    assertEquals(res, item1);
  }
  @Test
  void testToString() {
    // arrange
    item1 = new GroceryItem(new Ingredient("banana"), new Quantity(2), Unit.PIECE);
    item2 = new GroceryItem(new Ingredient("nutella"), new Quantity(200), Unit.GRAM);
    list = new GroceryList(item1, item2);
    
    List<GroceryItem> items = list.getItems();
    String actual = "GroceryList{entries=" + items + '}';
    
    // act
    String res = list.toString();
    
    // assert
    assertEquals(res, actual);
  }
}
