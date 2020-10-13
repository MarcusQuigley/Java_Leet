package com.leetcode.recursion;

public class RecursionLearning {

    public static void printReverse(char[] str) {
        printReverseWorker(str, str.length - 1);
    }

    private static void printReverseWorker(char[] str, int i) {
        if (i < 0)
            return;
        System.out.print(str[i]);
        printReverseWorker(str, i - 1);
    }

    public static void reverseString(char[] s) {
        System.out.println("Begin");
        printArray(s);
        reverseStringWorker(s, 0, s.length - 1);
        System.out.println("End");
        printArray(s);
    }

    static void reverseStringWorker(char[] s, int begin, int end) {
        if (begin < end) {
            char temp = s[begin];
            s[begin] = s[end];
            s[end] = temp;
            //begin++; end--;
            reverseStringWorker(s, begin+1, end-1);
        }
    }

    static void printArray(char[] s) {
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
    }
}
