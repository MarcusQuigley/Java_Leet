package com.leetcode.trees;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import com.leetcode.datastructures.TreeNode;

public class TreeProblemsInterviewCake {

	// https://www.interviewcake.com/question/csharp/balanced-binary-tree?course=fc1&section=trees-graphs
	// depth between leaf nodes is no greater than 1
	public boolean IsSuperBalanced(TreeNode root) {
		if (root == null)
			return false;
		Queue<Entry<TreeNode, Integer>> q = new LinkedList<Entry<TreeNode, Integer>>();
		q.add(new SimpleEntry<TreeNode, Integer>(root, 0));
		List<Integer> list = new ArrayList<>(3);
		while (!q.isEmpty()) {
			var current = q.remove();
			var node = current.getKey();
			if (node.left == null && node.right == null) {
				if (!list.contains(current.getValue())) {
					if (list.size() >= 2)
						return false;
					list.add(current.getValue());
				}

			} else {
				if (node.left != null)
					q.add(new SimpleEntry<TreeNode, Integer>(node.left, current.getValue() + 1));
				if (node.right != null)
					q.add(new SimpleEntry<TreeNode, Integer>(node.right, current.getValue() + 1));
			}
		}
		return (list.size() == 1) ? true : Math.abs(list.get(0) - list.get(1)) == 1;
	}

	public boolean validBST(TreeNode root) {
		return validBSTWorker(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

	}

	boolean validBSTWorker(TreeNode root, int lower, int upper) {
		if (root == null)
			return true;
		if (root.val < lower || root.val > upper)
			return false;
		return validBSTWorker(root.left, lower, root.val) && validBSTWorker(root.right, root.val, upper);
	}

	public boolean validBSTIter(TreeNode root) {
		if (root == null)
			return true;
		Deque<NodeBound> stack = new ArrayDeque<>();
		stack.push(new NodeBound(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		while (!stack.isEmpty()) {
			var c = stack.pop();
			if (c.node.val < c.lowerBound || c.node.val > c.upperBound)
				return false;
			if (c.node.left != null)
				stack.push(new NodeBound(c.node.left, c.lowerBound, c.node.val));
			if (c.node.right != null)
				stack.push(new NodeBound(c.node.right, c.node.val, c.upperBound));
		}
		return true;
	}

	class NodeBound {
		public TreeNode node;
		public int lowerBound;
		public int upperBound;

		public NodeBound() {
		}

		public NodeBound(TreeNode node, int lbound, int ubound) {
			this.node = node;
			this.lowerBound = lbound;
			this.upperBound = ubound;
		}
	}
}
