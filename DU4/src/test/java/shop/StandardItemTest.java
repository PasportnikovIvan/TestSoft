package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StandardItemTest {

    @Test
    public void testConstructor() {

        //ACT
        StandardItem standardItem = new StandardItem(1, "ItemName1", 2.99F, "CategoryName1", 21);

        //ASSERT
        Assertions.assertEquals(1, standardItem.getID());
        Assertions.assertEquals("ItemName1", standardItem.getName());
        Assertions.assertEquals(2.99F, standardItem.getPrice());
        Assertions.assertEquals("CategoryName1", standardItem.getCategory());
        Assertions.assertEquals(21, standardItem.getLoyaltyPoints());
    }

    @Test
    public void copy_returnsStandardItem_standardItem1(){
        //ARRANGE
        StandardItem standardItem1 = new StandardItem(1, "ItemName1", 2.99F, "CategoryName1", 21);

        //ACT
        StandardItem standardItem2 = standardItem1.copy();

        //ASSERT
        Assertions.assertEquals(standardItem1, standardItem2);
        //Assertions.assertTrue(standardItem1.equals(standardItem2));
    }

    @ParameterizedTest(name = "Item {0} = Item {5} is {10}")
    @CsvSource({"1, a, 1F, a, 1, 1, a, 1F, a, 1, true",
            "1, a, 1F, a, 1, 2, b, 2F, b, 2, false",
            "1, a, 1F, a, 1, 1, a, 1F, a, 2, false"})
    public void equals_itemAequalsItemB_returnsBool(
            int id1, String name1, float price1, String category1, int loyaltyPoints1,
            int id2, String name2, float price2, String category2, int loyaltyPoints2,
            boolean equality
    ) {
        //ARRANGE
        StandardItem standardItem1 = new StandardItem(id1, name1, price1, category1, loyaltyPoints1);
        StandardItem standardItem2 = new StandardItem(id2, name2, price2, category2, loyaltyPoints2);

        //ACT
        boolean item1EqualsItem2 = standardItem1.equals(standardItem2);

        //ASSERT
        Assertions.assertEquals(equality, item1EqualsItem2);
    }
}
