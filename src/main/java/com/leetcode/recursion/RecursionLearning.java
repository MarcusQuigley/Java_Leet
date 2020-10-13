package com.leetcode.recursion;
import java.util.ArrayList;
import java.util.List;

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

    //f(i,j)=f(i−1,j−1)+f(i−1,j)
    public static List<List<Integer>> pascalTriangle(int numRows) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        if (numRows == 0)
            return answer;

        answer.add(new ArrayList<>());
        answer.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            answer.add(new ArrayList<>());
            answer.get(i).add(1);
            for (int j = 1; j < i; j++) {
                int leftUp = answer.get(i - 1).get(j - 1);
                int rightUp = answer.get(i - 1).get(j);
                answer.get(i).add(leftUp + rightUp);
            }
            answer.get(i).add(1);
        }
        return answer;
    }
}
