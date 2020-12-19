package com.leetcode.arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArraysProblemsEasyTest {

	ArraysProblemsEasy sut = null;

	public ArraysProblemsEasyTest() {
		sut = new ArraysProblemsEasy();
	}

	// @formatter:off
	static Stream<Arguments> source_fib() {
		return Stream.of(arguments(3, 2), arguments(1, 1), arguments(2, 1), arguments(3, 2), arguments(4, 3),
				arguments(5, 5), arguments(6, 8), arguments(8, 21), arguments(0, 0));
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

	static Stream<Arguments> source_moveZeroes() {
		return Stream.of(arguments(new int[] { 5, 4, 0, 3, 0 }, new int[] { 5, 4, 3, 0, 0 })

		);
	}

	@ParameterizedTest
	@MethodSource("source_moveZeroes")
	void test_moveZeroes(int[] array, int[] expected) {
		sut.moveZeroes(array);
		assertArrayEquals(expected, array);
	}

	static Stream<Arguments> source_sortedSquares() {
		return Stream.of(arguments(new int[] { -4, -1, 0, 3, 10 }, new int[] { 0, 1, 9, 16, 100 })
				,arguments(new int[] { -7, -3, 2, 3, 11 }, new int[] { 4, 9, 9, 49, 121 })
				,arguments(new int[] { -1 }, new int[] { 1 })
				,arguments(new int[] { -3,-3,-3,1 }, new int[] { 1,9,9,9 })
				,arguments(new int[] {  }, new int[] {  })
				);
	}

	@ParameterizedTest
	@MethodSource("source_sortedSquares")
	void test_sortedSquares(int[] array, int[] expected) {
		var actual = sut.sortedSquares(array);
		assertArrayEquals(expected, actual);
	}

	
	// @formatter:on
}
