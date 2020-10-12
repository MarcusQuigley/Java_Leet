package com.leetcode.tests.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArraysProblemsEasyTest {
    static Stream<Arguments> source_fib() {
        return Stream.of(arguments(3, 2)
                , arguments(1, 1)
                , arguments(2, 1)
                , arguments(3, 2)
                , arguments(4, 3)
                , arguments(5,5)
                , arguments(6, 8)
                , arguments(8, 21)
        );
    }

    @ParameterizedTest
    @MethodSource("source_fib")
    void test_fibTopDown(int n, int expected) {
        int actual = ArraysProblemsEasy.fibTopDown(n);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("source_fib")
    void test_fibMemoTopDown(int n, int expected) {
        int actual = ArraysProblemsEasy.fibMemoTopDown(n);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("source_fib")
    void test_fibMemoBottomUp(int n, int expected) {
        int actual = ArraysProblemsEasy.fibMemoBottomUp(n);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("source_fib")
    void test_fibBottomUpNoMemo(int n, int expected) {
        int actual = ArraysProblemsEasy.fibBottomUpNoMemo(n);
        assertEquals(expected, actual);
    }

}
