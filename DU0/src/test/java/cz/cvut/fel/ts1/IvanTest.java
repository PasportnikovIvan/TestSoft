package cz.cvut.fel.ts1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class IvanTest {
    @Test
    public void factorialTest() {
        Assertions.assertEquals(120, Ivan.factorialRecursive(5));
    }
}

