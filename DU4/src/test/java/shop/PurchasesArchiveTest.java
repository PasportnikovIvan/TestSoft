package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class PurchasesArchiveTest {

    @Test
    public void printItemPurchaseStatistics_itemPurchaseArchiveIsEmpty_returnsStream(){
        //ARRANGE
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        PurchasesArchive purchasesArchive = new PurchasesArchive();

        //ACT
        purchasesArchive.printItemPurchaseStatistics();
        String result = myOut.toString().trim();

        //ASSERT

        Assertions.assertEquals("ITEM PURCHASE STATISTICS:", result);

        //CLEANUP
        System.setOut(System.out);
    }

    @Test
    public void printItemPurchaseStatistics_itemPurchaseArchiveIsFilled_returnsStream(){
        //ARRANGE
        //PREPARE OUT STREAM
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        //MOCK AND CONSTRUCT
        ItemPurchaseArchiveEntry mockedEntry = mock(ItemPurchaseArchiveEntry.class);
        when(mockedEntry.toString()).thenReturn("a");
        HashMap<Integer, ItemPurchaseArchiveEntry> hashMap = new HashMap<Integer, ItemPurchaseArchiveEntry>();
        hashMap.put(1, mockedEntry);
        ArrayList<Order> mockedOrders = mock(ArrayList.class);
        PurchasesArchive archive = new PurchasesArchive(hashMap, mockedOrders);

        //ACT
        archive.printItemPurchaseStatistics();
        String expectedResult = "ITEM PURCHASE STATISTICS:\na";
        String result = myOut.toString().trim();

        //ASSERT
        Assertions.assertEquals(expectedResult, result);

        //CLEANUP
        System.setOut(System.out);
    }

    @Test
    public void getHowManyTimesHasBeenItemSold_mockedItem_returnsCount(){
        //ARRANGE
        //PREPARE OUT STREAM
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        //MOCK AND CONSTRUCT
        Item mockedItem = mock(Item.class);
        ItemPurchaseArchiveEntry mockedPurchaseEntry = mock(ItemPurchaseArchiveEntry.class);
        HashMap<Integer, ItemPurchaseArchiveEntry> mockedItemArchive = mock(HashMap.class);
        when(mockedItemArchive.containsKey(anyInt())).thenReturn(true);
        when(mockedItemArchive.get(anyInt())).thenReturn(mockedPurchaseEntry);
        ArrayList<Order> mockedOrders = mock(ArrayList.class);
        PurchasesArchive archive = new PurchasesArchive(mockedItemArchive, mockedOrders);

        //ACT
        archive.getHowManyTimesHasBeenItemSold(mockedItem);

        //ASSERT
        verify(mockedPurchaseEntry, times(1)).getCountHowManyTimesHasBeenSold();

        //CLEANUP
        System.setOut(System.out);
    }

    @Test
    public void itemPurchaseArchiveEntry_testConstructor(){
        Item mockedItem = mock(Item.class);
        ItemPurchaseArchiveEntry entry = new ItemPurchaseArchiveEntry(mockedItem);

        Assertions.assertEquals(mockedItem, entry.getRefItem());
    }
}
