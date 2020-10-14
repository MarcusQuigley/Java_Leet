package com.leetcode.arrays;

public class ArraysProblemsEasy {
    //https://leetcode.com/problems/fibonacci-number/
    public static int fibTopDown(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fibTopDown(N - 1) + fibTopDown(N - 2);
    }

    public static int fibMemoTopDown(int N) {
        return fibMemoTopDownWorker(N, new int[N + 1]);
    }

    static int fibMemoTopDownWorker(int n, int[] memo) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] == 0)
            memo[n] = fibMemoTopDownWorker(n - 1, memo) + fibMemoTopDownWorker(n - 2, memo);
        return memo[n];
    }

    public static int fibMemoBottomUp(int n) {
        if (n < 2) return n;
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static int fibBottomUpNoMemo(int n) {
        if (n < 2) return n;
        int twoBack = 0;
        int oneBack = 1;
        for (int i = 2; i < n; i++) {
            int temp = twoBack + oneBack;
            twoBack = oneBack;
            oneBack = temp;
        }
        return twoBack + oneBack;
    }
}



