package com.leetcode.scratch;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

import com.leetcode.datastructures.TreeNode;

public class Scratch {
//	int CanweDoDFSWithQueue(TreeNode root){
//		
//	}
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		int sum = 0;
		Queue<TreeNode> stack = new LinkedList<>();
		stack.add(root);
		while (!stack.isEmpty()) {
			var current = stack.remove();
			if (current.val >= L && current.val <= R)
				sum += current.val;
			if (current.left != null)
				stack.add(current.left);
			if (current.right != null)
				stack.add(current.right);
		}
		return sum;
	}

//int max = Integer.MIN_VALUE;
	public int maxDepth(TreeNode root) {
		// Integer max = Integer.MIN_VALUE;
		// maxDepthWorker(root, max);
		if (root == null)
			return 0;
		var l = maxDepth(root.left);
		var r = maxDepth(root.right);

		return Math.max(l, r) + 1;

	}

//bfs
	public int maxDepthIter(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			var count = q.size();
			for (int i = 0; i < count; i++) {
				var curr = q.remove();
				if (curr.left != null)
					q.add(curr.left);
				if (curr.right != null)
					q.add(curr.right);
			}
			sum++;
		}
		return sum;
	}

	public int maxDepthIterDfs(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
		Queue<Entry<TreeNode, Integer>> q = new LinkedList<>();
		q.add(new SimpleEntry<TreeNode, Integer>(root, 1));
		while (!q.isEmpty()) {
			var curr = q.remove();
			var node = curr.getKey();
			if (node.left == null & node.right == null)
				sum = Math.max(sum, curr.getValue());
			else {
				if (node.left != null)
					q.add(new SimpleEntry<TreeNode, Integer>(node.left, curr.getValue() + 1));
				if (node.right != null)
					q.add(new SimpleEntry<TreeNode, Integer>(node.right, curr.getValue() + 1));
			}
		}
		return sum;
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list = new ArrayList<>();
		binaryTreePathsWorker(root, list, new StringBuilder());
		return list;
	}

	void binaryTreePathsWorker(TreeNode node, List<String> list, StringBuilder sb) {
		if (node == null)
			return;
		var len = sb.length();
		sb.append(node.val);
		if (node.left == null && node.right == null) {
			list.add(sb.toString());
		} else {
			sb.append("->");
			binaryTreePathsWorker(node.left, list, sb);
			binaryTreePathsWorker(node.right, list, sb);
		}
		sb.setLength(len);
	}

}
