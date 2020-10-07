package com.leetcode.tests.trees;
import com.leetcode.tests.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTestsBase {
    public TreeNode createBstTreeNodes(int[] values) {
        if (values == null || values.length == 0)
            return null;
        return inOrderBstTree(values, 0, values.length - 1);
    }

    TreeNode inOrderBstTree(int[] values, int left, int right) {
        if(left>right)
            return null;
        int mid = (left+right)/2;
        TreeNode node = new TreeNode((values[mid]));
        node.left = inOrderBstTree(values,left, mid-1);
        node.right=inOrderBstTree(values,mid+1,right);
        return node;
    }

    int[] ArrayListToArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
//        int[] result = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            result[i] = list.get(i);
//        }
//        return  result;
    }

    int[] TreeToArrayInOrder(TreeNode node) {
        List<Integer> array = new ArrayList<>();
        if (node != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = node;
            while (!stack.isEmpty() || current != null) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                array.add(current.val);
                current = current.right;
            }
        }
        return array.stream().mapToInt(i -> i).toArray();
    }

    public TreeNode createTreeNodes(int[] values) {
        if (values == null || values.length == 0)
            return null;
        TreeNode root = new TreeNode(values[0]);
        root = inOrderTree(values, root, 0);
        return root;
    }

    TreeNode inOrderTree(int[] values, TreeNode node, int index) {
        if (index < values.length) {
            if (values[index] != -666) {
                TreeNode temp = new TreeNode(values[index]);
                node = temp;
                node.left = inOrderTree(values, node.left, 2 * index + 1);
                node.right = inOrderTree(values, node.right, 2 * index + 2);
            }
        }
        return node;
    }
}

