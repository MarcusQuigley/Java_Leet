package com.leetcode.scratch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

//import org.junit.jupiter.api.Disabled;
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

	static Stream<Arguments> source_heightBinaryTree() {
		return Stream.of(arguments(new int[] { 1, 2 }, 2), arguments(new int[] { 1, 2, 3, -666, 5 }, 3),
				arguments(new int[] { 1, 2, 9, 5, 6, 7, -666, 3 }, 4),
				arguments(new int[] { 1, 2, 3, -666, -666, 5, 6, -666, -666, -666, -666, -666, -666, -666, 7, -666,
						-666, -666, -666, -666, -666, -666, -666, -666, -666, -666, -666, -666, -666, -666, 6 }, 5));
	}

	@ParameterizedTest
	@MethodSource("source_heightBinaryTree")
	void test_heightBinaryTree(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.heightBinaryTree1(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_isValidBST() {
		return Stream.of(arguments(new int[] { 2, 1, 3 }, true), arguments(new int[] { 1, 2, 3, -666, 5 }, false),
				arguments(new int[] { 5, 1, 4, -666, -666, 3, 6 }, false), arguments(new int[] { 1, 2 }, false),
				arguments(new int[] { 11, 2 }, true), arguments(new int[] { 1, -666, 1 }, false),
				arguments(new int[] { -2147483648, -2147483648 }, false), arguments(new int[] { -21, -21 }, false),
				arguments(new int[] { -2147483648, }, true),
				arguments(new int[] { -2147483648, -666, 2147483647 }, true),
				arguments(new int[] { 10, 5, 15, -666, -666, 6, 20 }, false)

		);
	}

	@ParameterizedTest
	@MethodSource("source_isValidBST")
	void test_isValidBST(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isValidBST(node);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_isValidBST")
	void test_isValidBSTIter(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isValidBSTIter(node);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_isValidBST")
	void test_isValidBST2(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isValidBST2(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_lowestCommonAncestor() {
		return Stream.of(arguments(new int[] { 6, 2, 8, 0, 4, 7, 9, -666, -666, 3, 5 }, 2, 8, 6),
				arguments(new int[] { 6, 2, 8, 0, 4, 7, 9, -666, -666, 3, 5 }, 2, 4, 2),
				arguments(new int[] { 2, 1 }, 2, 1, 2));
	}

	@ParameterizedTest
	@MethodSource("source_lowestCommonAncestor")
	void test_lowestCommonAncestor(int[] array, int p, int q, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.lowestCommonAncestor(node, new TreeNode(p), new TreeNode(q));

		assertEquals(expected, actual.val);
	}

	static Stream<Arguments> source_diameterOfBinaryTree() {
		return Stream.of(arguments(new int[] { 1, 2 }, 1), arguments(new int[] { 1, 2, 3, 4, 5 }, 3)
//				arguments(new int[] { 2, 1 }, 2, 1, 2)
		);
	}

	@ParameterizedTest
	@MethodSource("source_diameterOfBinaryTree")
	void test_diameterOfBinaryTree(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.diameterOfBinaryTree(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_isSymmetric() {
		return Stream.of(arguments(new int[] { 1, 2, 2, 3, 4, 4, 3 }, true),
				arguments(new int[] { 1, 2, 2, 4, 5, 5 }, false),
				arguments(new int[] { 1, 2, 2, -666, 4, 4, -666 }, true));
	}

	@ParameterizedTest
	@MethodSource("source_isSymmetric")
	void test_isSymmetric(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isSymmetric(node);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_isSymmetric")
	void test_isSymmetricIter(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isSymmetricIter(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_isSubtree() {
		return Stream.of(arguments(new int[] { 4, 1, 2 }, new int[] { 4, 1, 2 }, true),
				arguments(new int[] { 1 }, new int[] { 1 }, true),
				arguments(new int[] { 3, 4, 5, 1, 2 }, new int[] { 4, 1, 2 }, true));
	}

	@ParameterizedTest
	@MethodSource("source_isSubtree")
	void test_isSubtree(int[] array, int[] array2, boolean expected) {
		TreeNode node = createTreeNodes(array);
		TreeNode node2 = createTreeNodes(array2);
		var actual = sut.isSubtree(node, node2);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_isSubtree")
	void test_isSubtreeIter(int[] array, int[] array2, boolean expected) {
		TreeNode node = createTreeNodes(array);
		TreeNode node2 = createTreeNodes(array2);
		var actual = sut.isSubtreeIter(node, node2);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_sumEvenGrandparent() {
		return Stream.of(arguments(new int[] { 1, 2, 2, 4, }, 0), arguments(new int[] { 2, 2, 2, 4, }, 4),
				arguments(new int[] { 6, 7, 8, 2, 7, 1, 3, 9, -666, 1, 4, -666, -666, -666, 5 }, 18));

	}

	@ParameterizedTest
	@MethodSource("source_sumEvenGrandparent")
	void test_sumEvenGrandparentIer(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.sumEvenGrandparentIter(root);
		assertEquals(expected, (actual));
	}

	@ParameterizedTest
	@MethodSource("source_sumEvenGrandparent")
	void test_sumEvenGrandparent(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.sumEvenGrandparent(root);
		assertEquals(expected, (actual));
	}

	static Stream<Arguments> source_increasingBST() {
		return Stream.of(arguments(new int[] { 5, 3, 6 }, new int[] { 3, -666, 5, -666, 6 }),
				arguments(new int[] { 5, 3, 6, 2, 4, -666, 8, 1, -666, -666, -666, -666, -666, 7, 9 },
						new int[] { 1, -666, 2, -666, 3, -666, 4, -666, 5, -666, 6, -666, 7, -666, 8, -666, 9 }));
	}

	@ParameterizedTest
	@MethodSource("source_increasingBST")
	void test_increasingBSTIter(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		TreeNode actual = sut.increasingBSTIter(root);
		int[] listActual = ArrayFromTree(actual);
		assertArrayEquals(expected, listActual);
	}

}
