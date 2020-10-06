package com.leetcode.tests.trees;
import com.leetcode.tests.datastructures.TreeNode;




public class TreeTestsBase {
    public TreeNode createTreeNodes(int[] values) {
        if (values == null || values.length == 0)
            return null;
        return inOrderTree(values, 0, values.length - 1);
    }

    TreeNode inOrderTree(int[] values, int left, int right) {
        if(left>right)
            return null;
        int mid = (left+right)/2;
        TreeNode node = new TreeNode((values[mid]));
        node.left = inOrderTree(values,left, mid-1);
        node.right=inOrderTree(values,mid+1,right);
        return node;
    }


}

