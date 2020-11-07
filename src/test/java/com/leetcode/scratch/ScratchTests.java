package com.leetcode.scratch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.TreeNode;
import com.leetcode.trees.TreeTestsBase;

public class ScratchTests extends TreeTestsBase {

	Scratch sut;

	public ScratchTests() {
		sut = new Scratch();
	}

	static Stream<Arguments> source_rangeSumBST() {
		return Stream.of(arguments(new int[] { 1, 5, 7 }, 3, 5, 5), arguments(new int[] { 3, 5, 7, 10, 15 }, 7, 15, 32),
				arguments(new int[] { 1, 3, 5, 7, 10, 13, 15, 18 }, 6, 10, 17));
	}

	@ParameterizedTest
	@MethodSource("source_rangeSumBST")
	void test_rangeSumBST(int[] array, int left, int right, int expected) {
		TreeNode root = createBstTreeNodes(array);
		int actual = sut.rangeSumBST(root, left, right);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_maxDepth() {
		return Stream.of(arguments(new int[] { 3, 9, 20, -666, -666, 15, 7 }, 3), arguments(new int[] { 1, 1 }, 2),
				arguments(new int[] { 1, 2, 3, 4, -666, -666, 5 }, 3));
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepth(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = sut.maxDepth(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepthIter(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = sut.maxDepthIter(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepthIterDfs(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = sut.maxDepthIterDfs(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_binaryTreePaths() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, new String[] { "1->2", "1->3" }),
				arguments(new int[] { 1, 2, 3, -666, 5 }, new String[] { "1->2->5", "1->3" }),
				arguments(new int[] { 1, 2, 3, 5, 6 }, new String[] { "1->2->5", "1->2->6", "1->3" }));
	}

	@ParameterizedTest
	@MethodSource("source_binaryTreePaths")
	void test_binaryTreePaths(int[] array, String[] expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.binaryTreePaths(node);
		var actualAsArray = ArrayListToArrayString(actual);
		assertArrayEquals(expected, actualAsArray);
	}

	static Stream<Arguments> source_numberOfNodes() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, 3), arguments(new int[] { 1, 2, 3, -666, 5 }, 4),
				arguments(new int[] { 1, 2, 3, 5, 6, 7, 8, 9 }, 8));
	}

	@ParameterizedTest
	@MethodSource("source_numberOfNodes")
	void test_numberOfNodes(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.numberOfNodes(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_deepestNode() {
		return Stream.of(arguments(new int[] { 1, 2 }, 2), arguments(new int[] { 1, 2, 3, -666, 5 }, 5),
				arguments(new int[] { 1, 2, 9, 5, 6, 7, -666, 3 }, 3),
				arguments(new int[] { 1, 2, 3, -666, -666, 5, 6, -666, -666, -666, -666, -666, -666, 7, 6 }, 6));
	}

	@ParameterizedTest
	@MethodSource("source_deepestNode")
	void test_deepestNodeIter(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.deepestNodeIter(node);
		assertEquals(expected, actual.val);
	}

	@ParameterizedTest
	@MethodSource("source_deepestNode")
	void test_deepestNode(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.deepestNode(node);
		assertEquals(expected, actual.val);
	}
}
