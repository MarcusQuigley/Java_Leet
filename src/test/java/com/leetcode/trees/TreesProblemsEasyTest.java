package com.leetcode.trees;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.NaryNode;
import com.leetcode.datastructures.TreeNode;

public class TreesProblemsEasyTest extends TreeTestsBase {

	static Stream<Arguments> source_rangeSumBST() {
		return Stream.of(arguments(new int[] { 1, 5, 7 }, 3, 5, 5), arguments(new int[] { 3, 5, 7, 10, 15 }, 7, 15, 32),
				arguments(new int[] { 1, 3, 5, 7, 10, 13, 15, 18 }, 6, 10, 17));
	}

	@ParameterizedTest
	@MethodSource("source_rangeSumBST")
	void test_rangeSumBST(int[] array, int left, int right, int expected) {
		TreeNode root = createBstTreeNodes(array);
		int actual = TreesProblemsEasy.rangeSumBST(root, left, right);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_rangeSumBST")
	void test_rangeSumBSTIter(int[] array, int left, int right, int expected) {
		TreeNode root = createBstTreeNodes(array);
		int actual = TreesProblemsEasy.rangeSumBSTIter(root, left, right);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_getLonelyNodes() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, new int[] {}),
				arguments(new int[] { 3, 5, 7, 10 }, new int[] { 10 }), arguments(new int[] { 1, 3 }, new int[] { 3 }));
	}

	@ParameterizedTest
	@MethodSource("source_getLonelyNodes")
	void test_getLonelyNodes(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		List<Integer> actual = TreesProblemsEasy.getLonelyNodes(root);
		assertArrayEquals(expected, ArrayListToArray(actual));
	}

	@ParameterizedTest
	@MethodSource("source_getLonelyNodes")
	void test_getLonelyNodesIter(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		List<Integer> actual = TreesProblemsEasy.getLonelyNodesIter(root);
		assertArrayEquals(expected, ArrayListToArray(actual));
	}

	static Stream<Arguments> source_mergeTrees() {
		return Stream.of(arguments(new int[] { 1, 1 }, new int[] { 2, 2, 2 }, new int[] { 3, 3, 2 }),
				arguments(new int[] { 1, 2, 3 }, new int[] { 2, 2, 2 }, new int[] { 3, 4, 5 }),
				arguments(new int[] { 1, 3, 2, 5 }, new int[] { 3, 6, 1 }, new int[] { 4, 9, 3, 5 }));
	}
//    @ParameterizedTest
//    @MethodSource("source_mergeTrees")
//    void test_mergeTrees(int[] array1,int[] array2, int[] expected) {
//        TreeNode root1 = createTreeNodes(array1);
//        TreeNode root2 = createTreeNodes(array2);
//        TreeNode actual = ProblemsEasy.mergeTrees(root1, root2 );
//        assertArrayEquals(expected, TreeToArrayInOrder(actual));
//    }

	// [InlineData(new int[] { 4, 2, 7, -666, -666, 4 }, 2, new int[] { 2 })]

	static Stream<Arguments> source_searchBST() {
		return Stream.of(arguments(new int[] { 3, 2, 7, 1, 3 }, 2, new int[] { 2, 1, 3 }),
				arguments(new int[] { 3, 5, 7 }, 6, new int[] {}),
				arguments(new int[] { 4, 2, 7, -666, -666, 4 }, 2, new int[] { 2 }));
	}

	@ParameterizedTest
	@MethodSource("source_searchBST")
	void test_searchBST(int[] array, int val, int[] expected) {
		TreeNode root = createTreeNodes(array);
		TreeNode actual = TreesProblemsEasy.searchBST(root, val);
		int[] listActual = ArrayFromTree(actual);
		assertArrayEquals(expected, listActual);
	}

	@ParameterizedTest
	@MethodSource("source_searchBST")
	void test_searchBSTIter(int[] array, int val, int[] expected) {
		TreeNode root = createTreeNodes(array);
		TreeNode actual = TreesProblemsEasy.searchBSTIter(root, val);
		int[] listActual = ArrayFromTree(actual);
		assertArrayEquals(expected, listActual);
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
		TreeNode actual = TreesProblemsEasy.increasingBSTIter(root);
		int[] listActual = ArrayFromTree(actual);
		assertArrayEquals(expected, listActual);
	}

	@ParameterizedTest
	@MethodSource("source_increasingBST")
	void test_increasingBST(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		TreeNode actual = TreesProblemsEasy.increasingBST(root);
		int[] listActual = ArrayFromTree(actual);
		assertArrayEquals(expected, listActual);
	}

	static Stream<Arguments> source_sumRootToLeaf() {
		return Stream.of(arguments(new int[] { 1, 0, 1, 0, 1, 0, 1 }, 22));
	}

	@ParameterizedTest
	@MethodSource("source_sumRootToLeaf")
	void test_sumRootToLeaf(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.sumRootToLeaf(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_sumRootToLeaf")
	void test_sumRootToLeafIter(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.sumRootToLeafIter(root);
		assertEquals(expected, actual);
	}

	@Test
	void test_nary_MaxDepth() {
		NaryNode node5 = new NaryNode(5);
		NaryNode node6 = new NaryNode(6);

		List<NaryNode> kids3 = new ArrayList<NaryNode>();
		kids3.add(node5);
		kids3.add(node6);
		NaryNode node3 = new NaryNode(3, kids3);
		NaryNode node4 = new NaryNode(4);
		NaryNode node2 = new NaryNode(2);
		List<NaryNode> kids1 = new ArrayList<NaryNode>();
		kids1.add(node3);
		kids1.add(node2);
		kids1.add(node4);
		NaryNode node1 = new NaryNode(1, kids1);
		var expected = 3;
		var actual = TreesProblemsEasy.nary_MaxDepthIter(node1);
		assertEquals(expected, actual);
	}
}