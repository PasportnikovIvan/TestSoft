package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;

import static org.mockito.Mockito.mock;

public class ItemStockTest {

    @Test
    public void testConstructor() {
        //ARRANGE
        Item mockedItem = mock(Item.class);

        //ACT
        ItemStock itemStock = new ItemStock(mockedItem);

        //ASSERT
        Assertions.assertEquals(mockedItem, itemStock.getItem());
    }

    @ParameterizedTest(name = "Increased {0}x, Decreased {1}x, equals {2}")
    @CsvSource({"1, 2, -1", "15, 2, 13", "150, 6, 144", "1, 5, -4"})
    public void count_increaseAdecreaseB_returnsCount(int increase, int decrease, int count){
        //ARRANGE
        Item mockedItem = mock(Item.class);
        ItemStock itemStock = new ItemStock(mockedItem);

        //ACT
        itemStock.IncreaseItemCount(increase);
        itemStock.decreaseItemCount(decrease);
        int result = itemStock.getCount();

        //ASSERT
        Assertions.assertEquals(count, result);
    }
}
