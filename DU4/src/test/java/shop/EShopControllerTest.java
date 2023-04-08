package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.NoItemInStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;
import static shop.EShopController.newCart;
import static shop.EShopController.purchaseShoppingCart;

public class EShopControllerTest {
    ShoppingCart shoppingCart;
    ByteArrayOutputStream myOut;
    Item[] storageItems;

    @BeforeEach
    public void setupEshop(){
        //ARRANGE
        EShopController.storage = null;
        EShopController.startEShop();

        shoppingCart = newCart();

        storageItems = new Item[]{
                new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        // insert data to the storage
        int[] itemCount = {10,10,4,5,10,1};
        for (int i = 0; i < storageItems.length; i++) {
            EShopController.storage.insertItems(storageItems[i], itemCount[i]);
        }

        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }

    @Test
    public void purchaseEmptyShoppingCart(){
        //ACT
        try {
            EShopController.purchaseShoppingCart(shoppingCart, "Petr Pan", "Doma 123");
        } catch (NoItemInStorage e) {
            e.printStackTrace();
        }
        String result = myOut.toString().trim();

        //ASSERT
        Assertions.assertEquals("Error: shopping cart is empty", result);
    }

    @Test
    public void BuyingOutOfStockItem(){
        //ARRANGE
        String expectedResult = "STORAGE IS CURRENTLY CONTAINING:\n" +
                "STOCK OF ITEM:  Item   ID 1   NAME Dancing Panda v.2   CATEGORY GADGETS   PRICE 5000.0   LOYALTY POINTS 5    PIECES IN STORAGE: 10\n" +
                "STOCK OF ITEM:  Item   ID 2   NAME Dancing Panda v.3 with USB port   CATEGORY GADGETS   PRICE 6000.0   LOYALTY POINTS 10    PIECES IN STORAGE: 10\n" +
                "STOCK OF ITEM:  Item   ID 3   NAME Screwdriver   CATEGORY TOOLS   PRICE 200.0   LOYALTY POINTS 5    PIECES IN STORAGE: 4\n" +
                "STOCK OF ITEM:  Item   ID 4   NAME Star Wars Jedi buzzer   CATEGORY GADGETS   ORIGINAL PRICE 500.0    DISCOUNTED PRICE 35000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 5\n" +
                "STOCK OF ITEM:  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 24000.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 10\n" +
                "STOCK OF ITEM:  Item   ID 6   NAME Soft toy Angry bird (size 40cm)   CATEGORY GADGETS   ORIGINAL PRICE 800.0    DISCOUNTED PRICE 72000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 1" +
                "\nItem with ID 1 added to the shopping cart.\nItem with ID 1 removed from the shopping " +
                "cart.\nItem with ID 6 added to the shopping cart.\nItem with ID 6 added to the shopping cart.";
        Item item6 = storageItems[5];

        //ACT
        EShopController.storage.printListOfStoredItems();
        shoppingCart.addItem(storageItems[0]);
        shoppingCart.removeItem(1);
        shoppingCart.removeItem(5);

        shoppingCart.addItem(storageItems[5]);
        shoppingCart.addItem(storageItems[5]);

        //ASSERT
        //STRING PRINTS
        String result = myOut.toString().trim();
        Assertions.assertEquals(expectedResult, result);

        //EXCEPTION THROW
        Assertions.assertThrows(NoItemInStorage.class, () -> {purchaseShoppingCart(shoppingCart, "Bob Ross", "Doma 420");});

        //STOCK
        int itemStock = EShopController.storage.getItemCount(6);

        Assertions.assertEquals(0, itemStock);

        //SALES
        int itemSold6 = EShopController.archive.getHowManyTimesHasBeenItemSold(item6);
        Assertions.assertEquals(0, itemSold6);
    }

    @Test
    public void standardExperience(){
        //ARRANGE
        Item item4 = storageItems[3];
        Item item5 = storageItems[4];

        String printListOfStoreItems =
                "STORAGE IS CURRENTLY CONTAINING:\n" +
                        "STOCK OF ITEM:  Item   ID 1   NAME Dancing Panda v.2   CATEGORY GADGETS   PRICE 5000.0   LOYALTY POINTS 5    PIECES IN STORAGE: 10\n" +
                        "STOCK OF ITEM:  Item   ID 2   NAME Dancing Panda v.3 with USB port   CATEGORY GADGETS   PRICE 6000.0   LOYALTY POINTS 10    PIECES IN STORAGE: 10\n" +
                        "STOCK OF ITEM:  Item   ID 3   NAME Screwdriver   CATEGORY TOOLS   PRICE 200.0   LOYALTY POINTS 5    PIECES IN STORAGE: 4\n" +
                        "STOCK OF ITEM:  Item   ID 4   NAME Star Wars Jedi buzzer   CATEGORY GADGETS   ORIGINAL PRICE 500.0    DISCOUNTED PRICE 35000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 5\n" +
                        "STOCK OF ITEM:  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 24000.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 10\n" +
                        "STOCK OF ITEM:  Item   ID 6   NAME Soft toy Angry bird (size 40cm)   CATEGORY GADGETS   ORIGINAL PRICE 800.0    DISCOUNTED PRICE 72000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 1";
        String addItem = "\nItem with ID 1 added to the shopping cart.\nItem with ID 1 removed from the shopping cart.";
        String addItem2 = "\nItem with ID 4 added to the shopping cart.\nItem with ID 5 added to the shopping cart.\n";
        String printItemPurchaseStatistics =
                "ITEM PURCHASE STATISTICS:\n"+
                        "ITEM  Item   ID 4   NAME Star Wars Jedi buzzer   CATEGORY GADGETS   ORIGINAL PRICE 500.0    DISCOUNTED PRICE 35000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013   HAS BEEN SOLD 1 TIMES\n" +
                        "ITEM  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 24000.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013   HAS BEEN SOLD 1 TIMES\n";

        String printListOfStoreItems2 =
                "STORAGE IS CURRENTLY CONTAINING:\n"+
                        "STOCK OF ITEM:  Item   ID 1   NAME Dancing Panda v.2   CATEGORY GADGETS   PRICE 5000.0   LOYALTY POINTS 5    PIECES IN STORAGE: 10\n" +
                        "STOCK OF ITEM:  Item   ID 2   NAME Dancing Panda v.3 with USB port   CATEGORY GADGETS   PRICE 6000.0   LOYALTY POINTS 10    PIECES IN STORAGE: 10\n" +
                        "STOCK OF ITEM:  Item   ID 3   NAME Screwdriver   CATEGORY TOOLS   PRICE 200.0   LOYALTY POINTS 5    PIECES IN STORAGE: 4\n" +
                        "STOCK OF ITEM:  Item   ID 4   NAME Star Wars Jedi buzzer   CATEGORY GADGETS   ORIGINAL PRICE 500.0    DISCOUNTED PRICE 35000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 4\n" +
                        "STOCK OF ITEM:  Item   ID 5   NAME Angry bird cup   CATEGORY GADGETS   ORIGINAL PRICE 300.0    DISCOUNTED PRICE 24000.0  DISCOUNT FROM Sun Sep 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 9\n" +
                        "STOCK OF ITEM:  Item   ID 6   NAME Soft toy Angry bird (size 40cm)   CATEGORY GADGETS   ORIGINAL PRICE 800.0    DISCOUNTED PRICE 72000.0  DISCOUNT FROM Thu Aug 01 00:00:00 CEST 2013    DISCOUNT TO Sun Dec 01 00:00:00 CET 2013    PIECES IN STORAGE: 1";
        String expectedResult = printListOfStoreItems + addItem + addItem2 + printItemPurchaseStatistics + printListOfStoreItems2;

        //ACT
        EShopController.storage.printListOfStoredItems();
        shoppingCart.addItem(storageItems[0]);
        shoppingCart.removeItem(1);
        shoppingCart.removeItem(5);

        shoppingCart.addItem(item4);
        shoppingCart.addItem(item5);

        try {
            EShopController.purchaseShoppingCart(shoppingCart, "Karel Schwarzenberg", "Hrad 1");
        } catch (NoItemInStorage e) {
            e.printStackTrace();
        }
        EShopController.archive.printItemPurchaseStatistics();
        EShopController.storage.printListOfStoredItems();

        //ASSERT
        //PRINT MESSAGES
        String result = myOut.toString().trim();
        Assertions.assertEquals(expectedResult, result);

        //STORAGE STOCK
        int itemStock4 = EShopController.storage.getItemCount(4);
        int itemStock5 = EShopController.storage.getItemCount(5);
        Assertions.assertEquals(4, itemStock4);
        Assertions.assertEquals(9, itemStock5);

        //ARCHIVE
        int itemSold4 = EShopController.archive.getHowManyTimesHasBeenItemSold(item4);
        int itemSold5 = EShopController.archive.getHowManyTimesHasBeenItemSold(item5);
        Assertions.assertEquals(1, itemSold4);
        Assertions.assertEquals(1, itemSold5);
    }
}
