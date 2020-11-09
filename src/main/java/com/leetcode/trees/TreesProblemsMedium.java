package com.leetcode.trees;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.leetcode.datastructures.NaryNode;
import com.leetcode.datastructures.TreeNode;

//import javafx.util.Pair;

public class TreesProblemsMedium {
	// https://leetcode.com/problems/binary-tree-right-side-view/

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null)
			rightSideViewWorker(root, 0, list);
		return list;
	}

	private void rightSideViewWorker(TreeNode node, int level, List<Integer> list) {
		if (node == null)
			return;
		if (list.size() == level)
			list.add(node.val);
		rightSideViewWorker(node.right, level + 1, list);
		rightSideViewWorker(node.left, level + 1, list);
	}

	public List<Integer> rightSideViewIter(TreeNode root) {
		if (root == null)
			return new ArrayList<Integer>();
		List<Integer> list = new ArrayList<>();
		Deque<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while (!q.isEmpty()) {
			var size = q.size();
			for (int i = 0; i < size; i++) {
				var current = q.pop();
				if (i + 1 == size)
					list.add(current.val);
				if (current.left != null)
					q.add(current.left);
				if (current.right != null)
					q.add(current.right);
			}
		}
		return list;
	}

	// https://leetcode.com/problems/validate-binary-search-tree/
	public boolean isValidBST(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		var current = root;
		Integer prev = null;
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (prev != null) {
				if (current.val <= prev)
					return false;
			}
			prev = current.val;
			current = current.right;
		}
		return true;
	}
}
