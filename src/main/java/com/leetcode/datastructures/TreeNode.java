package com.leetcode.datastructures;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode l, TreeNode r) {
		this.val = val;
		this.left = l;
		this.right = r;
	}

	@Override
	public String toString() {
		return Integer.toString(this.val);
	}
}
