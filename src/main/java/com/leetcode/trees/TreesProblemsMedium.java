package com.leetcode.trees;

///import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Queue;
//import java.util.Set;
import java.util.Stack;

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

	// https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if (original == null || original == target)
			return cloned;

		TreeNode res = getTargetCopy(original.left, cloned.left, target);
		if (res != null)
			return res;
		return getTargetCopy(original.right, cloned.right, target);
	}

	public final TreeNode getTargetCopyIter(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if (original == null || cloned == null || target == null)
			return null;
		Queue<TreeNode[]> q = new LinkedList<TreeNode[]>();
		q.add(new TreeNode[] { original, cloned });
		while (!q.isEmpty()) {
			var nodes = q.remove();
			TreeNode orig = nodes[0];
			TreeNode clone = nodes[1];
			if (orig.val == target.val)
				return clone;
			else {
				if (orig.left != null)
					q.add(new TreeNode[] { orig.left, clone.left });
				if (orig.right != null)
					q.add(new TreeNode[] { orig.right, clone.right });
			}
		}
		return null;
	}

	public final TreeNode getTargetCopyWithDups(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if (original == null || original == target)
			return cloned;
		Queue<TreeNode[]> q = new LinkedList<TreeNode[]>();
		q.add(new TreeNode[] { original, cloned });
		while (!q.isEmpty()) {
			var nodes = q.remove();
			TreeNode orig = nodes[0];
			TreeNode clone = nodes[1];
			if (orig.val == target.val && isSameTree(orig, target))
				return clone;
			else {
				if (orig.left != null)
					q.add(new TreeNode[] { orig.left, clone.left });
				if (orig.right != null)
					q.add(new TreeNode[] { orig.right, clone.right });
			}
		}
		return null;

	}

	private boolean isSameTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null || t2 == null)
			return false;
		return t1.val == t2.val && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
	}
}
