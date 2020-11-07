package com.leetcode.runner;

import com.leetcode.recursion.RecursionLearning;
import com.leetcode.scratch.Scratch;
import com.leetcode.trees.TreeTestsBase;

public class RunProblems {
	public static void main(String[] args) {
		// RecursionLearning.printReverse("Hello".toCharArray());
		// RecursionLearning.reverseString("ReverseString".toCharArray());
		// RecursionLearning.climbStairs(6);
		runScratch();

	}

	private static void runScratch() {
		var sut = new Scratch();
		int[] vals = new int[] { 1, 2, 3, -666, 5 };
		var node = TreeTestsBase.createTreeNodesStatic(vals);
		var result = sut.deepestNode(node);
		System.out.println("ans " + result.val + " should be 5");
	}
}
