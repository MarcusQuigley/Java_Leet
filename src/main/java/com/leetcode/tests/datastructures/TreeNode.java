package com.leetcode.tests.datastructures;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }
    public  TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode l, TreeNode r) {
        this.val = val;
        this.left = l;
        this.right = r;
    }
}
