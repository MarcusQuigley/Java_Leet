package com.leetcode.scratch;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayDeque;

import com.leetcode.datastructures.TreeNode;

public class Scratch {
//	int CanweDoDFSWithQueue(TreeNode root){
//		
//	}
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		int val = 0;
		if (root.val >= L && root.val <= R)
			val += root.val;
		var l = (root.val >= L) ? rangeSumBST(root.left, L, R) : 0;
		var r = (root.val <= R) ? rangeSumBST(root.right, L, R) : 0;

		return val + l + r;
	}

	public int rangeSumBSTIter(TreeNode root, int L, int R) {
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

	public int numberOfNodes(TreeNode root) {
		if (root == null)
			return 0;

		return numberOfNodes(root.left) + 1 + numberOfNodes(root.right);
	}

	public TreeNode deepestNodeIter(TreeNode root) {
		if (root == null)
			return null;
		Deque<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while (!q.isEmpty()) {
			var size = q.size();
			for (int i = 0; i < size; i++) {
				var node = q.remove();
				if (node.left == null && node.right == null && q.isEmpty()) {
					return node;

				} else {
					if (node.left != null)
						q.add(node.left);
					if (node.right != null)
						q.add(node.right);
				}
			}
		}
		return null;
	}

//	int maxdN2 = 0;
//	TreeNode deepestN2 = null;
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	private class deepestNodeState {
		public int maxdN2 = 0;
		public TreeNode deepestN2 = null;
	}

	public TreeNode deepestNode(TreeNode root) {
		if (root == null)
			return null;
		var state = new deepestNodeState();
		deepestNodeWorker(root, 0, state);
		// map.forEach((k, v) -> System.out.println("node: " + k + " level: " + v));
		return state.deepestN2;
	}

	private void deepestNodeWorker(TreeNode node, int level, deepestNodeState state) {
		if (node == null)
			return;
		map.put(node.val, level);
		if (node.left == null && node.right == null) {
			state.maxdN2 = Math.max(state.maxdN2, level);
			if (level == state.maxdN2)
				state.deepestN2 = node;
		} else {
			++level;
			deepestNodeWorker(node.left, level, state);
			deepestNodeWorker(node.right, level, state);
		}
	}

	public int heightBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		var l = heightBinaryTree(root.left);
		var r = heightBinaryTree(root.right);
		return Math.max(l, r) + 1;
	}

	int heighthBT = 0;

	public int heightBinaryTree1(TreeNode root) {
		if (root == null)
			return 0;
		heightBinaryTreeWorker(root, 1);
		return heighthBT;
	}

	private void heightBinaryTreeWorker(TreeNode root, int level) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			heighthBT = Math.max(heighthBT, level);
			return;
		} else {
			level++;
			heightBinaryTreeWorker(root.left, level);
			heightBinaryTreeWorker(root.right, level);
		}
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		return isValidBSTWorker(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean isValidBSTWorker(TreeNode node, long low, long high) {

		if (node.val <= low || node.val >= high)
			return false;
		if (node.left != null && (node.right != null))
			return isValidBSTWorker(node.left, low, node.val) && isValidBSTWorker(node.right, node.val, high);

		if (node.left != null)
			return isValidBSTWorker(node.left, low, node.val);
		if (node.right != null)
			return isValidBSTWorker(node.right, node.val, high);
		return true;
	}

	private class isValidBSTstate {
		public TreeNode node;
		public Integer low;
		public Integer high;

		public isValidBSTstate(TreeNode node, Integer low, Integer high) {
			this.node = node;
			this.low = low;
			this.high = high;
		}
	}

	public boolean isValidBSTIter(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		Stack<isValidBSTstate> stack = new Stack<>();
		stack.add(new isValidBSTstate(root, null, null));
		while (!stack.isEmpty()) {
			var state = stack.pop();
			if (state.low != null && state.low >= state.node.val)
				return false;
			if (state.high != null && state.high <= state.node.val)
				return false;
			if (state.node.left != null)
				stack.push(new isValidBSTstate(state.node.left, state.low, state.node.val));
			if (state.node.right != null)
				stack.push(new isValidBSTstate(state.node.right, state.node.val, state.high));
		}
		return true;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		return (p.val > q.val) ? lowestCommonAncestorWorker(root, q, p) : lowestCommonAncestorWorker(root, p, q);
	}

	public TreeNode lowestCommonAncestorWorker(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;
		if (p.val <= root.val && q.val >= root.val)
			return root;
		if (root.val > p.val)
			return lowestCommonAncestor(root.left, p, q);
		else
			return lowestCommonAncestor(root.right, p, q);
	}

	int maxdOBT = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;

		diameterOfBinaryTreeWorker(root);
		return maxdOBT;
	}

	private int diameterOfBinaryTreeWorker(TreeNode root) {
		if (root == null)
			return 0;
		var l = diameterOfBinaryTreeWorker(root.left);
		var r = diameterOfBinaryTreeWorker(root.right);
		maxdOBT = Math.max(maxdOBT, l + r);
		return Math.max(l, r) + 1;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return false;
		return isSymmetricWorker(root, root);
	}

	private boolean isSymmetricWorker(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null || t2 == null)
			return false;
		return (t1.val == t2.val && isSymmetricWorker(t1.left, t2.right) && isSymmetricWorker(t1.right, t2.left));
	}

	public boolean isSymmetricIter(TreeNode root) {
		if (root == null)
			return false;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.remove();
			TreeNode t2 = q.remove();
			if (t1 == null && t2 == null)
				continue;
			if (t1 == null || t2 == null)
				return false;
			if (t1.val != t2.val)
				return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}

	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null)
			return false;

		if (isSameTree(s, t))
			return true;
		else
			return isSubtree(s.left, t) || isSubtree(s.right, t);

		// return false;
	}

	private boolean isSameTree(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;
		if (s.val != t.val)
			return false;
		return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
	}

	public boolean isSubtreeIter(TreeNode s, TreeNode t) {
		if (s == null)
			return false;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(s);
		while (!q.isEmpty()) {
			TreeNode node = q.remove();
			if (node == null)
				return false;
			if (node.val == t.val && isSameIterWorker(node, t))
				return true;
			else {
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
		}
		return false;
	}

	private boolean isSameIterWorker(TreeNode s, TreeNode t) {

		Queue<TreeNode> q = new LinkedList<>();

		q.add(s);
		q.add(t);
		while (!q.isEmpty()) {
			TreeNode node1 = q.remove();
			TreeNode node2 = q.remove();
			if (node1 == null && node2 == null)
				continue;
			if (node1 == null || node2 == null)
				return false;
			if (node1.val != node2.val)
				return false;
			q.add(node1.left);
			q.add(node2.left);
			q.add(node1.right);
			q.add(node2.right);
		}
		return true;
	}
}
