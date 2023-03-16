package cz.cvut.fel.ts1;

import org.junit.jupiter.api.*;

public class CalculatorTest {
    //ARRANGE
    static Calculator calc;

    @BeforeAll
    public static void initiateVariable() {
        calc = new Calculator();
    }

    @Test
    public void add_returnsNumber_12() {
        //ACT
        int result = calc.add(5,7);
        int expectedResult = 12;
        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void subtract_returnNumber_420() {
        //ACT
        int result = calc.subtract(419, -1);
        int expectedResult = 420;
        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void multiply_returnNumber_144() {
        //ACT
        int result = calc.multiply(12, 12);
        int expectedResult = 144;
        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void divide_returnsNumber_16() throws Exception {
        //ACT
        int result = calc.divide(32, 2);
        int expectedResult = 16;
        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void division_exceptionThrown_exception() {
        //ACT
        Exception exception = Assertions.assertThrows(Exception.class, () -> calc.divide(5,0));
        String expectedMessage = "Cannot divide by 0";
        String actualMessage = exception.getMessage();
        // ASSERT 2
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void exceptionThrownTest_exceptionThrown_exception() {
        // ACT + ASSERT 1 (Obsahuje assert, zda byla vyhozena výjimka očekávaného typu)
        Exception exception = Assertions.assertThrows(Exception.class, () -> calc.exceptionThrown());
        String expectedMessage = "Cannot divide by 0";
        String actualMessage = exception.getMessage();
        // ASSERT 2
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


}

