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

	TreesProblemsEasy sut;

	public TreesProblemsEasyTest() {
		sut = new TreesProblemsEasy();
	}

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

	NaryNode populateNaryNodes() {
		var emptyList = new ArrayList<NaryNode>();
		NaryNode node5 = new NaryNode(5, emptyList);
		NaryNode node6 = new NaryNode(6, emptyList);

		List<NaryNode> kids3 = new ArrayList<NaryNode>();
		kids3.add(node5);
		kids3.add(node6);
		NaryNode node3 = new NaryNode(3, kids3);
		NaryNode node4 = new NaryNode(4, emptyList);
		NaryNode node2 = new NaryNode(2, emptyList);
		List<NaryNode> kids1 = new ArrayList<NaryNode>();
		kids1.add(node3);
		kids1.add(node2);
		kids1.add(node4);
		NaryNode node1 = new NaryNode(1, kids1);
		return node1;
	}

	@Test
	void test_nary_MaxDepthIter() {
		var node = populateNaryNodes();
		var expected = 3;
		var actual = TreesProblemsEasy.nary_MaxDepthIter(node);
		assertEquals(expected, actual);
	}

	@Test
	void test_nary_MaxDepth() {
		var node = populateNaryNodes();
		var expected = 3;
		var actual = TreesProblemsEasy.nary_MaxDepth(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_isUnivalTree() {
		return Stream.of(arguments(new int[] { 1, 1, 1, 1, 1, 1 }, true),
				arguments(new int[] { 1, 1, 1, 4, 1 }, false));
	}

	@ParameterizedTest
	@MethodSource("source_isUnivalTree")
	void test_isUnivalTree(int[] array, boolean expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.isUnivalTree(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_isUnivalTree")
	void test_isUnivalTreeIter(int[] array, boolean expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.isUnivalTreeIter(root);
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
		var actual = TreesProblemsEasy.maxDepth(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_maxDepth")
	void test_maxDepthIter(int[] array, int expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.maxDepthIter(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_invertTree() {
		return Stream.of(arguments(new int[] { 4, 2, 7, 1, 3, 6, 9 }, new int[] { 4, 7, 2, 9, 6, 3, 1 }),
				arguments(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 }));
	}

	@ParameterizedTest
	@MethodSource("source_invertTree")
	void test_invertTree(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.invertTree(root);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	@ParameterizedTest
	@MethodSource("source_invertTree")
	void test_invertTreeIter(int[] array, int[] expected) {
		TreeNode root = createTreeNodes(array);
		var actual = TreesProblemsEasy.invertTreeIter(root);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	static Stream<Arguments> source_leafSimilar() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 }, true),
				arguments(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 }, false));
	}

	@ParameterizedTest
	@MethodSource("source_leafSimilar")
	void test_leafSimilarIter(int[] array1, int[] array2, boolean expected) {
		TreeNode node1 = createTreeNodes(array1);
		TreeNode node2 = createTreeNodes(array2);
		var actual = TreesProblemsEasy.leafSimilarIter(node1, node2);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_leafSimilar")
	void test_leafSimilarBetter(int[] array1, int[] array2, boolean expected) {
		TreeNode node1 = createTreeNodes(array1);
		TreeNode node2 = createTreeNodes(array2);
		var actual = TreesProblemsEasy.leafSimilarBetter(node1, node2);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_averageOfLevels() {
		return Stream.of(arguments(new int[] { 3, 9, 20, -666, -666, 15, 7 }, new double[] { 3, 14.5, 11 }), arguments(
				new int[] { 2147483647, 2147483647, 2147483647 }, new double[] { 2147483647.00000, 2147483647.00000 }));
	}

	@ParameterizedTest
	@MethodSource("source_averageOfLevels")
	void test_averageOfLevels(int[] array, double[] expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.averageOfLevels(node);
		assertArrayEquals(expected, actual.stream().mapToDouble(i -> i).toArray());
	}

	static Stream<Arguments> source_trimBST() {
		return Stream.of(arguments(new int[] { 1, 0, 2 }, 1, 2, new int[] { 1, -666, 2 }), arguments(
				new int[] { 3, 0, 4, -666, 2, -666, -666, -666, -666, 1 }, 1, 3, new int[] { 3, 2, -666, 1 }));
	}

	@ParameterizedTest
	@MethodSource("source_trimBST")
	void test_trimBST(int[] array, int low, int high, int[] expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.trimBST(node, low, high);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	static Stream<Arguments> source_sortedArrayToBST() {
		return Stream.of(arguments(new int[] { -10, -3, 0 }, new int[] { -3, -10, 0 })
		// , arguments( new int[] { 3, 0, 4, -666, 2, -666, -666, -666, -666, 1 }, 1, 3,
		// new int[] { 3, 2, -666, 1 })
		);
	}

	@ParameterizedTest
	@MethodSource("source_sortedArrayToBST")
	void test_sortedArrayToBST(int[] array, int[] expected) {
		var actual = TreesProblemsEasy.sortedArrayToBST(array);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	@ParameterizedTest
	@MethodSource("source_sortedArrayToBST")
	void test_sortedArrayToBSTIter(int[] array, int[] expected) {
		var actual = TreesProblemsEasy.sortedArrayToBSTIter(array);
		assertArrayEquals(expected, ArrayFromTree(actual));
	}

	static Stream<Arguments> source_findTarget() {
		return Stream.of(arguments(new int[] { 5, 3, 6, 2, 4, -666, 7 }, 28, false),
				arguments(new int[] { 5, 3, 6, 2, 4, -666, 7 }, 9, true), arguments(new int[] { 1 }, 2, false)
		// new int[] { 3, 2, -666, 1 })
		);
	}

	@ParameterizedTest
	@MethodSource("source_findTarget")
	void test_findTarget(int[] array, int k, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.findTarget(node, k);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_tree2str() {
		return Stream.of(arguments(new int[] { 1, 2, 3, 4 }, "1(2(4))(3)"),
				arguments(new int[] { 1, 2, 3, -666, 4 }, "1(2()(4))(3)"), arguments(new int[] { 1 }, "1"));
	}

	@ParameterizedTest
	@MethodSource("source_tree2str")
	void test_tree2str(int[] array, String expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.tree2str(node);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_tree2str")
	void test_tree2strIter(int[] array, String expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.tree2strIter(node);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_levelOrderBottom() {
		return Stream.of(arguments(new int[] { 3, 9, 20, -666, -666, 15, 7 }));
	}

	@ParameterizedTest
	@MethodSource("source_levelOrderBottom")
	void test_levelOrderBottom(int[] array) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.levelOrderBottom(node);

		int[][] expected = new int[3][];
		expected[0] = new int[] { 15, 7 };
		expected[1] = new int[] { 9, 20 };
		expected[2] = new int[] { 3 };

		var actualLength = actual.size() - 1;
		var expectedLength = expected.length - 1;
		assertEquals(actualLength, expectedLength);
		while (actualLength > -1 && expectedLength >= 0) {
			assertArrayEquals(ArrayListToArray(actual.get(actualLength--)), expected[expectedLength--]);
		}
	}

	static Stream<Arguments> source_isSameTree() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 }, true),
				arguments(new int[] { 1, 2 }, new int[] { 1, -666, 2 }, false),
				arguments(new int[] { 1, 1 }, new int[] { 1, -666, 1 }, false));
	}

	@ParameterizedTest
	@MethodSource("source_isSameTree")
	void test_isSameTree(int[] array1, int[] array2, boolean expected) {
		TreeNode node1 = createTreeNodes(array1);
		TreeNode node2 = createTreeNodes(array2);
		var actual = TreesProblemsEasy.isSameTree(node1, node2);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_binaryTreePaths() {
		return Stream.of(arguments(new int[] { 1, 2, 3 }, new String[] { "1->2", "1->3" }),
				arguments(new int[] { 1, 2, 3, -666, 5 }, new String[] { "1->2->5", "1->3" }),
				arguments(new int[] { 1, 2, 3, 5, 6 }, new String[] { "1->2->5", "1->2->6", "1->3" }));
	}

	@ParameterizedTest
	@MethodSource("source_binaryTreePaths")
	void test_isSameTree(int[] array, String[] expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.binaryTreePaths(node);
		var actualAsArray = ArrayListToArrayString(actual);
		assertArrayEquals(expected, actualAsArray);
	}

	@ParameterizedTest
	@MethodSource("source_binaryTreePaths")
	void test_binaryTreePathsIter(int[] array, String[] expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.binaryTreePathsIter(node);
		var actualAsArray = ArrayListToArrayString(actual);
		assertArrayEquals(expected, actualAsArray);
	}

	static Stream<Arguments> source_getMinimumDifference() {
		return Stream.of(arguments(new int[] { 1, -666, 3, -666, -666, -666, 2 }, 1));
	}

	@ParameterizedTest
	@MethodSource("source_getMinimumDifference")
	void test_getMinimumDifferenceIter(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.getMinimumDifferenceIter(node);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_getMinimumDifference")
	void test_getMinimumDifference(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.getMinimumDifference(node);

		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_sumOfLeftLeaves() {
		return Stream.of(arguments(new int[] { 1, 9, 20, -666, -666, 15, 7 }, 24),
				arguments(new int[] { 1, 2, 3, 4, 5 }, 4));
	}

	@ParameterizedTest
	@MethodSource("source_sumOfLeftLeaves")
	void test_sumOfLeftLeaves(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.sumOfLeftLeaves(node);

		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_sumOfLeftLeaves")
	void test_sumOfLeftLeavesIter(int[] array, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.sumOfLeftLeavesIter(node);

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
		var actual = TreesProblemsEasy.lowestCommonAncestor(node, new TreeNode(p), new TreeNode(q));

		assertEquals(expected, actual.val);
	}

	@ParameterizedTest
	@MethodSource("source_lowestCommonAncestor")
	void test_lowestCommonAncestorIter(int[] array, int p, int q, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.lowestCommonAncestorIter(node, new TreeNode(p), new TreeNode(q));

		assertEquals(expected, actual.val);
	}

	static Stream<Arguments> source_closestValueIter() {
		return Stream.of(arguments(new int[] { 4, 3, 5, 1, 3 }, 3.714286, 4)
//				arguments(new int[] { 6, 2, 8, 0, 4, 7, 9, -666, -666, 3, 5 }, 2, 4, 2),
//				arguments(new int[] { 2, 1 }, 2, 1, 2)
		);
	}

	@ParameterizedTest
	@MethodSource("source_closestValueIter")
	void test_closestValueIter(int[] array, double target, int expected) {
		TreeNode node = createTreeNodes(array);
		var actual = TreesProblemsEasy.closestValueIter(node, target);

		assertEquals(expected, actual);
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
		var actual = TreesProblemsEasy.diameterOfBinaryTree(node);
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

}