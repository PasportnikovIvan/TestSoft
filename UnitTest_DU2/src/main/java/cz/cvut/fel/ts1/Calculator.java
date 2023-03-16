package cz.cvut.fel.ts1;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a-b;
    }
    public int multiply(int a, int b) {
        return a*b;
    }
    public int divide(int a, int b) throws Exception {
        if (b==0) {
            return exceptionThrown();
        }
        else {
            return a/b;
        }
    }

    int exceptionThrown() throws Exception {
        throw new Exception("Cannot divide by 0");
    }
}
