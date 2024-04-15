package main.java.com.practice.dynamicProgramming;

public class DynamicProgramming {

    // | null | null | 1 | 2 | 3 | 5 | 8 | 13 ...
    static Integer[] memo = new Integer[100];

    public static int fibonacci(int n) {
        if (memo[n] != null) {
            return memo[n];
        }

        if (n == 1 || n == 0) {
            return n;
        }

        memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return memo[n];
    }

    public static int fibonacciBottomUp(int n) {
        int[] fibList = new int[n + 1];
        fibList[0] = 0;
        fibList[1] = 1;

        for (int i = 2; i <= n ; i++) {
            fibList[i] = fibList[n-1] + fibList[n-2];
        }
        return fibList[n];
    }
}
