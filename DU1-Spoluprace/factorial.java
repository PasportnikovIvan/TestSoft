package cz.cvut.fel.ts1;

public class Assyldam {
    public long factorial (int n) {
        if (n == 0){
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
}
