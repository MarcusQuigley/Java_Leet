package com.leetcode.recursion;

public class RecursionLearning {

    public static void printReverse(char [] str) {
        printReverseWorker(str, str.length-1);
    }

    private static void printReverseWorker(char[] str, int i) {
        if (i < 0)
            return;
        System.out.print(str[i]);
        printReverseWorker(str, i-1);
    }

}
