package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class OrderTest {

    @Test
    public void testConstructorWithState(){
        //ARRANGE
        ShoppingCart mockedShoppingCart = mock(ShoppingCart.class);

        //ACT
        Order order = new Order(mockedShoppingCart, "customer1", "address 1", 1);

        //ASSERT
        verify(mockedShoppingCart,times(1)).getCartItems();
        Assertions.assertEquals("customer1", order.getCustomerName());
        Assertions.assertEquals("address 1", order.getCustomerAddress());
        Assertions.assertEquals(1, order.getState());

        Order order2 = new Order(mockedShoppingCart, null, null, 12);
        verify(mockedShoppingCart,times(2)).getCartItems();
        Assertions.assertNull(order2.getCustomerName());
        Assertions.assertNull(order2.getCustomerAddress());
        Assertions.assertEquals(12, order2.getState());
    }

    @Test
    public void testConstructorWithState_nullValues(){
        //ARRANGE
        ShoppingCart mockedShoppingCart = mock(ShoppingCart.class);

        //ACT
        Order order2 = new Order(mockedShoppingCart, null, null, 12);

        //ASSERT
        verify(mockedShoppingCart,times(1)).getCartItems();
        Assertions.assertNull(order2.getCustomerName());
        Assertions.assertNull(order2.getCustomerAddress());
        Assertions.assertEquals(12, order2.getState());
    }

    @Test
    public void testConstructor(){
        //ARRANGE
        ShoppingCart mockedShoppingCart = mock(ShoppingCart.class);

        //ACT
        Order order = new Order(mockedShoppingCart, "customer1",
                "address 1");

        //ASSERT
        verify(mockedShoppingCart,times(1)).getCartItems();
        Assertions.assertEquals("customer1", order.getCustomerName());
        Assertions.assertEquals("address 1", order.getCustomerAddress());
        Assertions.assertEquals(0, order.getState());
    }

    @Test
    public void testConstructor_nullValues(){
        //ARRANGE
        ShoppingCart mockedShoppingCart = mock(ShoppingCart.class);

        //ACT
        Order order2 = new Order(mockedShoppingCart, null, null);

        //ASSERT
        verify(mockedShoppingCart,times(1)).getCartItems();
        Assertions.assertNull(order2.getCustomerName());
        Assertions.assertNull(order2.getCustomerAddress());
        Assertions.assertEquals(0, order2.getState());
    }
}
