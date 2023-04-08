package cz.cvut.fel.ts1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class factorialTest {
    @Test
    public void factorialTest() {
        factorial fac = new factorial();
        int num = 3;
        long expectedresult = 6;

        long result = fac.factorial(num);

        Assertions.assertEquals(expectedresult, result);
    }
}
