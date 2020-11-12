package com.leetcode.trees;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.TreeNode;
import com.leetcode.scratch.Scratch;
import com.leetcode.trees.TreeTestsBase;

public class TreesProblemsMediumTests extends TreeTestsBase {

	TreesProblemsMedium sut;

	public TreesProblemsMediumTests() {
		sut = new TreesProblemsMedium();
	}

	static Stream<Arguments> source_isValidBST() {
		return Stream.of(arguments(new int[] { 2, 1, 3 }, true), arguments(new int[] { 1, 2, 3, -666, 5 }, false),
				arguments(new int[] { 5, 1, 4, -666, -666, 3, 6 }, false), arguments(new int[] { 1, 2 }, false),
				arguments(new int[] { 11, 2 }, true), arguments(new int[] { 1, -666, 1 }, false),
				arguments(new int[] { -2147483648, -2147483648 }, false), arguments(new int[] { -21, -21 }, false),
				arguments(new int[] { -2147483648, }, true),
				arguments(new int[] { -2147483648, -666, 2147483647 }, true));
	}

	@ParameterizedTest
	@MethodSource("source_isValidBST")
	void test_isValidBST(int[] array, boolean expected) {
		TreeNode root = this.createTreeNodes(array);
		boolean actual = sut.isValidBST(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_rightSideView() {
		return Stream.of(arguments(new int[] { 2, 1, 3 }, new int[] { 2, 3 }),
				arguments(new int[] { 1, 2, 3, -666, 5 }, new int[] { 1, 3, 5 }),
				arguments(new int[] { 5, 1, 4, -666, -666, 3, 6 }, new int[] { 5, 4, 6 }),
				arguments(new int[] { 1, 2 }, new int[] { 1, 2 }),
				arguments(new int[] { 1, -666, 1 }, new int[] { 1, 1 }), arguments(new int[] { -2, }, new int[] { -2 }),
				arguments(new int[] { 1, 2, 3, -666, 5, -666, 4 }, new int[] { 1, 3, 4 }));

	}

	@ParameterizedTest
	@MethodSource("source_rightSideView")
	void test_rightSideView(int[] array, int[] expected) {
		TreeNode root = this.createTreeNodes(array);
		List<Integer> actual = sut.rightSideView(root);
		assertArrayEquals(expected, ArrayListToArray(actual));
	}

	@ParameterizedTest
	@MethodSource("source_rightSideView")
	void test_rightSideViewIter(int[] array, int[] expected) {
		TreeNode root = this.createTreeNodes(array);
		List<Integer> actual = sut.rightSideViewIter(root);
		assertArrayEquals(expected, ArrayListToArray(actual));
	}

	static Stream<Arguments> source_deepestLeavesSum() {
		return Stream.of(arguments(new int[] { 1, 2, 3, 4, 5, -666, 6, 7, -666, -666, -666, -666, -666, -666, 8 }, 15));

	}

	@ParameterizedTest
	@MethodSource("source_deepestLeavesSum")
	void test_deepestLeavesSum(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.deepestLeavesSum(root);
		assertEquals(expected, (actual));
	}

	@ParameterizedTest
	@MethodSource("source_deepestLeavesSum")
	void test_deepestLeavesSumIter(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.deepestLeavesSumIter(root);
		assertEquals(expected, (actual));
	}

}
