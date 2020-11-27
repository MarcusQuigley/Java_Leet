package com.leetcode.trees;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.TreeNode;
//import com.leetcode.scratch.Scratch;

public class TreesProblemsMediumTests extends TreeTestsBase {

	TreesProblemsMedium sut;

	public TreesProblemsMediumTests() {
		sut = new TreesProblemsMedium();
	}

	// @formatter:off
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
		boolean actual = sut.isValidBSTIter(root);
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

	static Stream<Arguments> source_sumEvenGrandparent() {
		return Stream.of(arguments(new int[] { 1, 2, 2, 4, }, 0), arguments(new int[] { 2, 2, 2, 4, }, 4),
				arguments(new int[] { 6, 7, 8, 2, 7, 1, 3, 9, -666, 1, 4, -666, -666, -666, 5 }, 18));

	}

	@ParameterizedTest
	@MethodSource("source_sumEvenGrandparent")
	void test_sumEvenGrandparent(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.sumEvenGrandparent(root);
		assertEquals(expected, (actual));
	}

	@ParameterizedTest
	@MethodSource("source_sumEvenGrandparent")
	void test_sumEvenGrandparentIter(int[] array, int expected) {
		TreeNode root = this.createTreeNodes(array);
		int actual = sut.sumEvenGrandparentIter(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_constructMaximumBinaryTree() {
		return Stream.of(arguments(new int[] { 3, 2, 1, 6, 0, 5 },
				new int[] { 6, 3, 5, -666, 2, 0, -666, -666, -666, -666, 1 }));

	}

	@ParameterizedTest
	@MethodSource("source_constructMaximumBinaryTree")
	void test_constructMaximumBinaryTree(int[] array, int[] expected) {

		TreeNode actual = sut.constructMaximumBinaryTree(array);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}

	static Stream<Arguments> source_lowestCommonAncestor() {
		return Stream.of(arguments(new int[] { 3, 5, 1, 6, 2, 0, 8, -666, -666, 7, 4 }, 5, 1, 3),
				arguments(new int[] { 3, 5, 1, 6, 2, 0, 8, -666, -666, 7, 4 }, 5, 4, 5),
				arguments(new int[] { 1, 2 }, 1, 2, 1), arguments(new int[] { 1, 2, 3 }, 2, 3, 1));

	}

	@ParameterizedTest
	@MethodSource("source_lowestCommonAncestor")
	void test_lowestCommonAncestorIter(int[] array, int pVal, int qVal, int expected) {
		TreeNode root = this.createTreeNodes(array);
		TreeNode p = super.nodeFromNode(root, pVal);
		TreeNode q = super.nodeFromNode(root, qVal);
		if (p == null || q == null)
			assertFalse(false);
		else {
			TreeNode actual = sut.lowestCommonAncestorIter(root, p, q);
			assertEquals(expected, actual.val);
		}
	}

	@ParameterizedTest
	@MethodSource("source_lowestCommonAncestor")
	void test_lowestCommonAncestor(int[] array, int pVal, int qVal, int expected) {
		TreeNode root = this.createTreeNodes(array);
		TreeNode p = super.nodeFromNode(root, pVal);
		TreeNode q = super.nodeFromNode(root, qVal);
		if (p == null || q == null)
			assertFalse(false);
		else {
			TreeNode actual = sut.lowestCommonAncestor(root, p, q);
			assertEquals(expected, actual.val);
		}
	}

	static Stream<Arguments> source_bstFromPreorder() {
		return Stream.of(
				arguments(new int[] { 8, 5,  1 }, new int[] { 8, 5, -666,1 })
					,arguments(new int[] { 8, 5, 1, 7, 10, 12 }, new int[] { 8, 5, 10, 1, 7, -666, 12 })
					,arguments(new int[] { 4,5,14,20 }, new int[] { 4,-666, 5,  -666, 14,  -666, 20 })
				  , arguments(new int[] { 2, 6, 5, 4, 9 }, new int[] { 2, -666, 6,   5, 9,   4 })
				);

	}

	@ParameterizedTest
	@MethodSource("source_bstFromPreorder")
	void test_bstFromPreorder(int[] array, int[] expected) {
		TreeNode actual = sut.bstFromPreorder(array);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}
	
	@ParameterizedTest
	@MethodSource("source_bstFromPreorder")
	void test_bstFromPreorderIter(int[] array, int[] expected) {
		TreeNode actual = sut.bstFromPreorderIter(array);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}
	
	static Stream<Arguments> source_getAllElements() {
		return Stream.of(
				arguments(new int[] {2,1,4 }, new int[] {1,0,3 },new int[] {0,1,1,2,3,4 })
					,arguments(new int[] {0,-10,10 }, new int[] {5,1,7,0,2 },new int[] {-10,0,0,1,2,5,7,10})
					,arguments(new int[] {  }, new int[] {5,1,7,0,2 },new int[] {0,1,2,5,7})
				  , arguments(new int[] {0,-10,10 }, new int[] {  },new int[] {-10,0,10 })
				);
	}

	@ParameterizedTest
	@MethodSource("source_getAllElements")
	void test_getAllElements(int[] array1,int[] array2, int[] expected) {
		var node1 = super.createTreeNodes(array1);
		var node2 = super.createTreeNodes(array2);
		List<Integer> actual = sut.getAllElementsIter(node1, node2);
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	
	static Stream<Arguments> source_findNearestRightNode() {
		return Stream.of(
				arguments(new int[] {1,2,3,-666,4,5,6 },4,5))
				;
	}

	@ParameterizedTest
	@MethodSource("source_findNearestRightNode")
	void test_findNearestRightNode(int[] array,int u, int expected) {
		var node = super.createTreeNodes(array);
		var nodeToFind = super.nodeFromNode(node,  u);
		TreeNode actual = sut.findNearestRightNode(node,nodeToFind);
		assertEquals(expected, actual.val);
	}
	
	static Stream<Arguments> source_insertIntoBST() {
		return Stream.of(
				arguments(new int[] {4,2,7,1,3 },5,new int[] {4,2,7,1,3,5 })
					,arguments(new int[] {40,20,60,10,30,50,70},25,new int[] {40,20,60,10,30,50,70,-666,-666,25 })
					,arguments(new int[] {},5, new int[] {5})
				);
	}

	@ParameterizedTest
	@MethodSource("source_insertIntoBST")
	void test_insertIntoBST(int[] array, int val, int[] expected) {
		var node = super.createTreeNodes(array);
		 
		TreeNode actual = sut.insertIntoBST(node, val);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}
	
	@ParameterizedTest
	@MethodSource("source_insertIntoBST")
	void test_insertIntoBSTIter(int[] array, int val, int[] expected) {
		var node = super.createTreeNodes(array);
		 
		TreeNode actual = sut.insertIntoBSTIter(node, val);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}	
	
//	static Stream<Arguments> source_pruneTree() {
//		return Stream.of(
//				arguments(new int[] { 1,-666,0,-666,-666,0,1 } ,new int[] {1,-666,0,-666,1})
//					,arguments(new int[] { 1,0,1,0,0,0,1 } ,new int[] {1,-666,1,-666,1})
//					,arguments(new int[] {1,1,0,1,1,0,1,0 } ,new int[] {1,1,0,1,1,-666,1})
//				);
//	}
//
//	@ParameterizedTest
//	@MethodSource("source_pruneTree")
//	void test_pruneTree(int[] array,  int[] expected) {
//		var node = super.createTreeNodes(array);
//		 
//		TreeNode actual = sut.pruneTree(node);
//		assertArrayEquals(expected, super.ArrayFromTree(actual));
//	}
	
	static Stream<Arguments> source_removeLeafNodes() {
		return Stream.of(
				arguments(new int[] { 1,3,3,3,2 } ,3, new int[] {1,3,-666,-666,2})
					,arguments(new int[] { 1,2,3 },2 ,new int[] {1,-666,3})
					,arguments(new int[] { 1,2,-666,2 },2 ,new int[] {1})
					,arguments(new int[] {1,2,3,2,-666,2,4 },2 ,new int[] {1,-666,3,-666,4})
				);
	}

	@ParameterizedTest
	@MethodSource("source_removeLeafNodes")
	void test_removeLeafNodes(int[] array, int target, int[] expected) {
		var node = super.createTreeNodes(array);
		TreeNode actual = sut.removeLeafNodes(node,target);
		assertArrayEquals(expected, super.ArrayFromTree(actual));
	}
	
	static Stream<Arguments> source_inorder() {
		return Stream.of(
				arguments(new int[] { 1,-666,2,-666,-666,3 } , new int[] {1,3,2})
					,arguments(new int[] { 4,2,8,1,3,6 },new int[] {1,2,3,4,6,8})
					,arguments(new int[] { 1,2  } ,new int[] {2,1})
					,arguments(new int[] {1,-666,2 } ,new int[] {1,2})
				);
	}
	
	
	@ParameterizedTest
	@MethodSource("source_inorder")
	void test_inorderTraversal(int[] array,  int[] expected) {
		var node = super.createTreeNodes(array);
		List<Integer> actual = sut.inorderTraversal(node);
	 
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	@ParameterizedTest
	@MethodSource("source_inorder")
	void test_inorderTraversalIter(int[] array,  int[] expected) {
		var node = super.createTreeNodes(array);
		List<Integer> actual = sut.inorderTraversalIter(node);
	 
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	
	@ParameterizedTest
	@MethodSource("source_inorder")
	void test_inorderTraversalIntuitive(int[] array,  int[] expected) {
		var node = super.createTreeNodes(array);
		List<Integer> actual = sut.inorderTraversalIterIntuitive(node);
	 
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	
	static Stream<Arguments> source_postOrderTraversal() {
		return Stream.of(
				arguments(new int[] {1, 2 } , new int[] {2,1})
//					,arguments(new int[] { 4, 2, 8, 1, 3, 6 },new int[] { 1, 3, 2, 6, 8, 4})
//					,arguments(new int[] { 1, 4, 3, 2  } ,new int[] { 2, 4, 3, 1})
//					,arguments(new int[] {1, -666, 2, -666, -666, 3 } ,new int[] {3, 2, 1})
//					,arguments(new int[] {   } ,new int[] { })
				);
	}
	
	@ParameterizedTest
	@MethodSource("source_postOrderTraversal")
	void test_postOrderTraversalIntuitive(int[] array,  int[] expected) {
		var node = super.createTreeNodes(array);
		List<Integer> actual = sut.postOrderTraversalIntuitive(node);
	 
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	
	@ParameterizedTest
	@MethodSource("source_postOrderTraversal")
	void test_postOrderTraversalWithPrev(int[] array,  int[] expected) {
		var node = super.createTreeNodes(array);
		List<Integer> actual = sut.postOrderTraversalWithPrevNode(node);
	 
		assertArrayEquals(expected, super.ArrayListToArray(actual));
	}
	
	
	
	// @formatter:on
}
