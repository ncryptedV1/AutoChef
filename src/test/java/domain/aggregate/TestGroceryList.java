package domain.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import de.cunc.autochef.domain.aggregate.GroceryList;
import de.cunc.autochef.domain.valueobject.GroceryItem;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test GroceryList")
public class TestGroceryList {

  @Test
  public void testConstructorList() {
    // arrange
    List<GroceryItem> items = mock(List.class);

    // act
    GroceryList list = new GroceryList(items);

    // assert
    assertNotNull(list);
  }

  @Test
  public void testConstructorVarArgs() {
    // arrange
    GroceryItem item1 = mock(GroceryItem.class);
    GroceryItem item2 = mock(GroceryItem.class);

    // act
    GroceryList list = new GroceryList(item1, item2);

    // assert
    assertNotNull(list);
  }

  @Test
  public void testGetItems() {
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
  public void testAddItem() {
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
}
