package com.leetcode.runner;

import com.leetcode.datastructures.TreeNode;
//import com.leetcode.recursion.RecursionLearning;
import com.leetcode.scratch.Scratch;
import com.leetcode.trees.TreeTestsBase;
import com.leetcode.trees.TreesProblemsEasy;
import com.leetcode.trees.TreesProblemsMedium;

@SuppressWarnings("unused")
public class RunProblems {
	public static void main(String[] args) {
		// RecursionLearning.printReverse("Hello".toCharArray());
		// RecursionLearning.reverseString("ReverseString".toCharArray());
		// RecursionLearning.climbStairs(6);
		// runScratch();
		// runTreesEasy();
		runNaryNode();
	}

	private static void runScratch() {
		var sut = new Scratch();
		int[] vals = new int[] { 1, 2, 3, -666, 5 };
		var node = TreeTestsBase.createTreeNodesStatic(vals);
		var result = sut.deepestNode(node);
		System.out.println("ans " + result.val + " should be 5");
	}

	private static void runTreesEasy() {
		var sut = new TreesProblemsEasy();

		int[] vals = new int[] { 3, 5, 7, -666, 15 };
		TreeNode node = TreeTestsBase.createTreeNodesStatic(vals);
		TreeNode result = sut.deepestNode(node);
		System.out.println("ans " + result.val + " should be 15");
	}

	private static void runNaryNode() {
		var sut = new TreesProblemsMedium();

	}

}
