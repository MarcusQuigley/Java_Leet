package com.leetcode.recursion;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.ListNode;
import com.leetcode.datastructures.TreeNode;
import com.leetcode.linkedlists.LinkedListHelper;
import com.leetcode.trees.TreeTestsBase;

public class RecursionLearningTest extends TreeTestsBase {
	static Stream<Arguments> source_rangeSumBST() {
		return Stream.of(arguments(new int[] { 4, 2, 7, 1, 3 }, 2, new int[] { 2, 1, 3 }));
	}

	@ParameterizedTest
	@MethodSource("source_rangeSumBST")
	void test_rangeSumBST(int[] array, int val, int[] expected) {
		TreeNode root = createTreeNodes(array);
		TreeNode actual = RecursionLearning.searchBST(root, val);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	static Stream<Arguments> source_pascalTriangle2() {
		return Stream.of(arguments(2, new int[] { 1, 2, 1 }), arguments(3, new int[] { 1, 3, 3, 1 }),
				arguments(1, new int[] { 1, 1 }), arguments(4, new int[] { 1, 4, 6, 4, 1 }));
	}

	@ParameterizedTest
	@MethodSource("source_pascalTriangle2")
	void test_pascalTriangle2(int rowIndex, int[] expected) {
		List<Integer> actual = RecursionLearning.pascalTriangle2(rowIndex);
		assertArrayEquals(expected, ArrayListToArray(actual));
	}

	static Stream<Arguments> source_fib() {
		return Stream.of(arguments(2, 1), arguments(3, 2), arguments(1, 1), arguments(4, 3), arguments(5, 5),
				arguments(6, 8), arguments(8, 21), arguments(0, 0));
	}

	@ParameterizedTest
	@MethodSource("source_fib")
	void test_fib(Integer n, Integer expected) {
		Integer actual = RecursionLearning.fib(n);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_climbStairs() {
		return Stream.of(arguments(2, 2), arguments(3, 3), arguments(1, 1), arguments(4, 5), arguments(5, 8),
				arguments(6, 13), arguments(8, 34));
	}

	@ParameterizedTest
	@MethodSource("source_climbStairs")
	void test_climbStairs(Integer n, Integer expected) {
		Integer actual = RecursionLearning.climbStairs(n);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_climbStairs")
	void test_climbStairsIter(Integer n, Integer expected) {
		Integer actual = RecursionLearning.climbStairsIterOld(n);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_sum_recursion() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, 6), arguments(new int[] { 1 }, 1),
				arguments(new int[] {}, 0));
	}

	@ParameterizedTest
	@MethodSource("source_sum_recursion")
	void test_sum_non_tail_recursion(int[] ls, int expected) {
		int actual = RecursionLearning.sum_non_tail_recursion(ls);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_sum_recursion")
	void test_sum_tail_recursion(int[] ls, int expected) {
		int actual = RecursionLearning.sum_tail_recursion(ls);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_maxDepth() {
		return Stream.of(arguments(new int[] { 1, 2, 3, 5 }, 3), arguments(new int[] { 1, 2 }, 2),
				arguments(new int[] { 1, 3, 5, 6, 7, 8, 9, 6 }, 4));
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepth(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		int actual = RecursionLearning.maxDepth(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepth_tail_worker(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		int actual = RecursionLearning.maxDepth_tail(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_myPow() {
		return Stream.of(arguments(2.0, 10, 1024.0), arguments(2.1, 3, 9.261000000000001), arguments(2.0, 3, 8.0),
				arguments(-2.0, 3, -8.0), arguments(-2.0, 4, 16.0), arguments(2.0, -2, 0.25),
				arguments(.44528, 0, 1.00000), arguments(0.00001, 2147483647, 1.00000));
	}

	@ParameterizedTest
	@Disabled
	@MethodSource("source_myPow")
	void test_myPow(double x, int n, double expected) {
		double actual = RecursionLearning.myPow(x, n);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_mergeTwoLists() {
		return Stream.of(arguments(new int[] { 1, 2, 4 }, new int[] { 1, 3, 4 }, new int[] { 1, 1, 2, 3, 4, 4 }),
				arguments(new int[] {}, new int[] {}, new int[] {}),
				arguments(new int[] { -9, 3 }, new int[] { 5, 7 }, new int[] { -9, 3, 5, 7 }),
				arguments(new int[] { 3, 17 }, new int[] { 5, 7 }, new int[] { 3, 5, 7, 17 }),
				arguments(new int[] {}, new int[] { 4 }, new int[] { 4 }));
	}

	@ParameterizedTest
	@MethodSource("source_mergeTwoLists")
	void test_mergeTwoListsIter(int[] arr1, int[] arr2, int[] expected) {
		ListNode l1 = LinkedListHelper.CreateLinkedList(arr1);
		ListNode l2 = LinkedListHelper.CreateLinkedList(arr2);
		ListNode actual = RecursionLearning.mergeTwoListsIter(l1, l2);
		assertArrayEquals(expected, LinkedListHelper.ListFromListNode(actual));
	}

	@ParameterizedTest
	@MethodSource("source_mergeTwoLists")
	void test_mergeTwoLists(int[] arr1, int[] arr2, int[] expected) {
		ListNode l1 = LinkedListHelper.CreateLinkedList(arr1);
		ListNode l2 = LinkedListHelper.CreateLinkedList(arr2);
		ListNode actual = RecursionLearning.mergeTwoLists(l1, l2);
		assertArrayEquals(expected, LinkedListHelper.ListFromListNode(actual));
	}

	static Stream<Arguments> source_kthGrammarBrute() {
		return Stream.of(arguments(1, 1, 0), arguments(2, 1, 0), arguments(2, 2, 1), arguments(4, 5, 1),
				arguments(4, 7, 0));
	}

	@ParameterizedTest
	@MethodSource("source_kthGrammarBrute")
	void test_kthGrammarBrute(int n, int k, int expected) {
		int actual = RecursionLearning.kthGrammar(n, k);
		assertEquals(expected, actual);
	}

}
