package cz.cvut.fel.ts1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MainTest {
    static Main main;
    @BeforeAll
    static public void initiateMain(){
        main = new Main();
        main.main(new String[]{"hi"});
    }

    @Test
    public void main_returnsString_yuh() {
        String expectedResult = "Ivan Pasportnikov";
        String result = main.paspoiva;
        Assertions.assertEquals(expectedResult, result);
    }
}


