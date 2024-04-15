package main.java.com.practice.recursion;

public class Factorial {
    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }
}
