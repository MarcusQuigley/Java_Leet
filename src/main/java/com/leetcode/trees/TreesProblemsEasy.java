package com.leetcode.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.leetcode.datastructures.NaryNode;
import com.leetcode.datastructures.TreeNode;

public class TreesProblemsEasy {
	// https://leetcode.com/problems/range-sum-of-bst
	public static int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		int val = 0;

		int left = (root.val > L) ? rangeSumBST(root.left, L, R) : 0;
		int right = (root.val < R) ? rangeSumBST(root.right, L, R) : 0;// rangeSumBST(root.right, L, R);
		if (root.val >= L && root.val <= R)
			val += root.val;
		return left + right + val;
	}

	public static int rangeSumBSTIter(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		int acc = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null)
				continue;
			acc += (node.val >= L && node.val <= R) ? node.val : 0;
			if (node.val >= L)
				queue.add(node.left);
			if (node.val <= R)
				queue.add(node.right);
		}
		return acc;
	}

	// https://leetcode.com/problems/find-all-the-lonely-nodes/
	public static List<Integer> getLonelyNodes(TreeNode root) {
		if (root == null)
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
		} else if (root.left == null && root.right != null) {
			orphans.add((root.right.val));
		}
		getLonelyNodesWorker(root.left, orphans);
		getLonelyNodesWorker(root.right, orphans);
	}

	public static List<Integer> getLonelyNodesIter(TreeNode root) {
		if (root == null)
			return null;
		List<Integer> orphans = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node == null)
				continue;
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

	// https://leetcode.com/problems/merge-two-binary-trees/
	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}

	// https://leetcode.com/problems/search-in-a-binary-search-tree/
	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return root;
		if (root.val == val)
			return root;
		return (root.val > val) ? searchBST(root.left, val) : searchBST(root.right, val);
	}

	public static TreeNode searchBSTIter(TreeNode root, int val) {
		if (root == null)
			return root;
		while (root != null) {
			if (root.val == val)
				break;
			root = (root.val > val) ? root.left : root.right;
		}
		return root;
	}

	public static List<Integer> preorderNaryIter(NaryNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root != null) {
			Stack<NaryNode> stack = new Stack<NaryNode>();
			stack.push(root);
			while (!stack.isEmpty()) {
				NaryNode current = stack.pop();
				result.add(current.val);
				for (int i = current.children.size() - 1; i >= 0; i--) {
					stack.push(current.children.get(i));
				}
			}
		}
		return result;
	}

	public static List<Integer> preorderNary(NaryNode root) {
		List<Integer> list = new ArrayList<Integer>();
		preorderNary_worker(root, list);
		return list;
	}

	static void preorderNary_worker(NaryNode root, List<Integer> list) {
		if (root == null)
			return;
		list.add(root.val);
		for (int i = 0; i < root.children.size(); i++)
			preorderNary_worker(root.children.get(i), list);
	}

	public List<Integer> postorderNary(NaryNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			Stack<NaryNode> left = new Stack<>();
			Stack<NaryNode> right = new Stack<>();
			left.push(root);
			while (!left.isEmpty()) {
				var current = left.pop();
				for (int i = 0; i < current.children.size(); i++) {
					left.push(current.children.get(i));
				}
				right.push(current);
			}
			while (!right.isEmpty()) {
				list.add(right.pop().val);
			}
		}
		return list;
	}
}
