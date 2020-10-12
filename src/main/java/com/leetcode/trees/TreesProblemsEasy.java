package com.leetcode.trees;

import com.leetcode.datastructures.TreeNode;
import java.util.*;
//import java.util.LinkedList;
//import java.util.Queue;

public class TreesProblemsEasy {
    //https://leetcode.com/problems/range-sum-of-bst
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int val = 0;

        int left = (root.val > L) ? rangeSumBST(root.left, L, R):0;
        int right =(root.val < R) ? rangeSumBST(root.right, L, R) : 0;// rangeSumBST(root.right, L, R);
        if(root.val >=L && root.val<=R)
            val+= root.val;
        return left+right+val;
    }

    public static int rangeSumBSTIter(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int acc=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null) continue;
             acc+=(node.val>=L && node.val <=R) ? node.val:0;
             if(node.val >=L)
                 queue.add(node.left);
             if(node.val<=R)
                queue.add(node.right);
        }
        return  acc;
    }

    //https://leetcode.com/problems/find-all-the-lonely-nodes/
    public static List<Integer> getLonelyNodes(TreeNode root) {
        if (root==null)
            return null;
        List<Integer> results = new ArrayList<>();
        getLonelyNodesWorker(root, results);
        return results;
    }

    static void getLonelyNodesWorker(TreeNode root, List<Integer> orphans) {
        if (root == null)
            return;
        if (root.left != null && root.right == null) {
            orphans.add((root.left.val));
        }
        else if(root.left == null && root.right != null) {
            orphans.add((root.right.val));
        }
        getLonelyNodesWorker(root.left, orphans);
        getLonelyNodesWorker(root.right, orphans);
    }

    public static List<Integer> getLonelyNodesIter(TreeNode root) {
        if (root==null)
            return null;
        List<Integer> orphans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) continue;
            if (node.left != null && node.right == null) {
                orphans.add((node.left.val));
            } else if (node.left == null && node.right != null) {
                orphans.add((node.right.val));
            }
            stack.push(node.left);
            stack.push(node.right);
        }
         return orphans;
    }

    //https://leetcode.com/problems/merge-two-binary-trees/
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    //https://leetcode.com/problems/search-in-a-binary-search-tree/
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return root;
        if (root.val == val)
            return root;
        return (root.val > val) ?   searchBST(root.left, val):  searchBST(root.right, val);
    }
    public static TreeNode searchBSTIter(TreeNode root, int val) {
        if (root == null)
            return root;
        while (root!=null){
            if(root.val==val)
                break;
            root = (root.val>val)? root.left:root.right;
        }
        return root;
    }

}
