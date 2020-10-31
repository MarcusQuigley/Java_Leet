package com.leetcode.trees;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
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
		// if (heap.size() <= 2) {
		if (list.size() == 1)
			return true;
		else
			return Math.abs(list.get(0) - list.get(1)) == 1;
	}
}
