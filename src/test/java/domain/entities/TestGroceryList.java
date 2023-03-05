package domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.entities.GroceryItem;
import de.cunc.autochef.domain.entities.GroceryList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test GroceryList")
public class TestGroceryList {

  @Test
  void testConstructorList() {
    // arrange
    List<GroceryItem> items = mock(List.class);
    
    // act
    GroceryList list = new GroceryList(items);
    
    // assert
    assertNotNull(list);
  }

  @Test
  void testConstructorVarArgs() {
    // arrange
    GroceryItem item1 = mock(GroceryItem.class);
    GroceryItem item2 = mock(GroceryItem.class);
    
    // act
    GroceryList list = new GroceryList(item1, item2);

    // assert
    assertNotNull(list);
  }
  
  @Test
  void testGetItems() {
    // arrange

    GroceryItem item1 = mock(GroceryItem.class);
    GroceryItem item2 = mock(GroceryItem.class);
    GroceryList list = new GroceryList(item1, item2);
    
    // act
    List<GroceryItem> items = list.getItems();
    
    // assert
    assertEquals(items.size(), 2);
  }
  
  @Test
  void testAddItem() {
    // arrange
    GroceryItem item1 = mock(GroceryItem.class);
    GroceryList list = new GroceryList();
    
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
    GroceryItem item1 = mock(GroceryItem.class);
    GroceryItem item2 = mock(GroceryItem.class);
    GroceryList list = new GroceryList(item1, item2);
    
    List<GroceryItem> items = list.getItems();
    String expected = "Zutaten:\n"
        + items.stream().map(item -> "- " + item.toString()).collect(Collectors.joining("\n"));
    
    // act
    String res = list.toString();
    
    // assert
    assertEquals(res, expected);
  }
}
