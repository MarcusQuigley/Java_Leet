package com.leetcode.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.leetcode.datastructures.NaryNode;
import com.leetcode.datastructures.TreeNode;

import javafx.util.Pair;

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

	public static TreeNode increasingBSTIter(TreeNode root) {
		if (root == null)
			return null;
		TreeNode result = new TreeNode(0);
		TreeNode tempNode = result;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			tempNode.right = new TreeNode(current.val);
			tempNode = tempNode.right;
			current = current.right;
		}
		return result.right;
	}

	public static TreeNode increasingBST(TreeNode root) {
		if (root == null)
			return null;
		List<Integer> list = new ArrayList<>();
		increasingBST_worker(root, list);
		TreeNode temp = new TreeNode(0);
		TreeNode result = temp;

		for (int i = 0; i < list.size(); i++) {
			temp.right = new TreeNode(list.get(i));
			temp = temp.right;
		}
		return result.right;
	}

	static void increasingBST_worker(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		increasingBST_worker(root.left, list);
		list.add(root.val);
		increasingBST_worker(root.right, list);
	}

	public static int sumRootToLeafIter(TreeNode root) {
		if (root == null)
			return 0;
		var sum = 0;
		Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
		stack.push(new Pair<TreeNode, Integer>(root, root.val));
		while (!stack.isEmpty()) {
			var current = stack.pop();
			var node = current.getKey();
			if (node.left == null && node.right == null)
				sum += current.getValue();
			else {
				if (node.left != null)
					stack.push(new Pair<TreeNode, Integer>(node.left, current.getValue() << 1 | node.left.val));
				if (node.right != null)
					stack.push(new Pair<TreeNode, Integer>(node.right, current.getValue() << 1 | node.right.val));
			}
		}
		return sum;
	}

	static int sumrtl = 0;

	public static int sumRootToLeaf(TreeNode root) {
		if (root != null)
			sumRootToLeafWorker(root, 0);
		return sumrtl;
	}

	static void sumRootToLeafWorker(TreeNode root, int val) {
		var acc = val << 1 | root.val;
		if (root.left == null && root.right == null) {
			sumrtl += acc;
			return;
		}
		if (root.left != null)
			sumRootToLeafWorker(root.left, acc);
		if (root.right != null)
			sumRootToLeafWorker(root.right, acc);
	}

	public static int nary_MaxDepthIter(NaryNode root) {
		if (root == null)
			return 0;
		int max = 0;
		Queue<Pair<NaryNode[], Integer>> q = new LinkedList<Pair<NaryNode[], Integer>>();
		q.add(new Pair<NaryNode[], Integer>(new NaryNode[] { root }, 1));
		while (!q.isEmpty()) {
			var row = q.remove();
			for (var n : row.getKey()) {
				if (n.children.size() > 0)
					q.add(new Pair<NaryNode[], Integer>(n.children.toArray(new NaryNode[0]), row.getValue() + 1));
				else
					max = Math.max(max, row.getValue());
			}
		}
		return max;
	}

	public static int nary_MaxDepth(NaryNode root) {
		if (root == null)
			return 0;
		int max = 0;
		for (NaryNode child : root.children) {
			int value = nary_MaxDepth(child);
			max = Math.max(max, value);
		}
		return max + 1;
	}

	public static boolean isUnivalTree(TreeNode root) {
		if (root == null)
			return false;
		return isUnivalTreeWorker(root, root.val);
	}

	static boolean isUnivalTreeWorker(TreeNode root, int value) {
		if (root == null)
			return true;
		return (root.val == value && isUnivalTreeWorker(root.left, value) && isUnivalTreeWorker(root.right, value));
	}

	public static boolean isUnivalTreeIter(TreeNode root) {
		if (root == null)
			return false;
		int value = root.val;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			var node = stack.pop();
			if (value != node.val)// stack.peek().val)
				return false;

			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		return true;
	}

	// https://leetcode.com/problems/maximum-depth-of-binary-tree/
	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		var left = maxDepth(root.left);
		var right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}

	public static int maxDepthIter(TreeNode root) {
		if (root == null)
			return 0;
		int max = 0;
		Deque<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while (!q.isEmpty()) {
			var count = q.size();

			for (int i = 0; i < count; i++) {
				var n = q.poll();
				if (n.left != null)
					q.add(n.left);
				if (n.right != null)
					q.add(n.right);
			}
			max++;
		}
		return max;
	}

	// https://leetcode.com/problems/invert-binary-tree/
	public static TreeNode invertTree(TreeNode root) {
		if (root == null)
			return root;
		var right = invertTree(root.right);
		var left = invertTree(root.left);

		root.left = right;
		root.right = left;
		return root;
	}

	public static TreeNode invertTreeIter(TreeNode root) {
		if (root == null)
			return null;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			var current = q.remove();
			var temp = current.left;
			current.left = current.right;
			current.right = temp;
			if (current.left != null)
				q.add(current.left);
			if (current.right != null)
				q.add(current.right);
		}
		return root;
	}

	// https://leetcode.com/problems/leaf-similar-trees/
	public static boolean leafSimilarIter(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		var listLeafs1 = leafSimilarWorker(root1);
		var listLeafs2 = leafSimilarWorker(root2);
		if (listLeafs1.size() != listLeafs2.size())
			return false;
		for (int i = 0; i < listLeafs1.size(); i++) {
			if (listLeafs1.get(i) != listLeafs2.get(i))
				return false;
		}
		return true;
	}

	static List<Integer> leafSimilarWorker(TreeNode node) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.add(node);
		while (!stack.isEmpty()) {
			var current = stack.pop();
			if (current.left == null && current.right == null)
				list.add(current.val);
			if (current.right != null)
				stack.push(current.right);
			if (current.left != null)
				stack.push(current.left);
		}
		return list;
	}

	public static boolean leafSimilarBetter(TreeNode root1, TreeNode root2) {
		Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
		s1.push(root1);
		s2.push(root2);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			if (dfs(s1) != dfs(s2))
				return false;
		}
		return s1.isEmpty() && s2.isEmpty();
	}

	static int dfs(Stack<TreeNode> stack) {
		while (true) {
			var current = stack.pop();
			if (current.left == null && current.right == null)
				return current.val;
			if (current.left != null)
				stack.push(current.left);
			if (current.right != null)
				stack.push(current.right);
		}
	}

	public static List<Double> averageOfLevels(TreeNode root) {
		List<Double> list = new ArrayList<>();
		if (root != null) {
			Deque<TreeNode> q = new ArrayDeque<>();
			q.add(root);
			while (!q.isEmpty()) {
				var levelAverage = 0.0d;
				var count = q.size();
				for (int i = 0; i < count; i++) {
					var node = q.remove();
					levelAverage += node.val;
					if (node.left != null)
						q.add(node.left);
					if (node.right != null)
						q.add(node.right);
				}
				list.add(levelAverage / count);
			}
		}
		return list;
	}

	public static TreeNode trimBST(TreeNode root, int low, int high) {
		if (root == null)
			return null;
		if (root.val == low) {
			root.left = null;
			root.right = trimBST(root.right, low, high);
			return root;
		}
		if (root.val == high) {
			root.right = null;
			root.left = trimBST(root.left, low, high);
			return root;
		}
		if (root.val > high) {
			return trimBST(root.left, low, high);
		}
		if (root.val < low) {
			return trimBST(root.right, low, high);
		}
		root.left = trimBST(root.left, low, high);
		root.right = trimBST(root.right, low, high);
		return root;
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;

		return sortedArrayToBSTWorker(nums, 0, nums.length - 1);
	}

	static TreeNode sortedArrayToBSTWorker(int[] nums, int low, int high) {
		if (low > high)
			return null;
		var length = high + low;
		var mid = length / 2;
//			if (length % 2 == 0)
//				mid++;
		TreeNode node = new TreeNode(nums[mid]);

		node.left = sortedArrayToBSTWorker(nums, low, mid - 1);
		node.right = sortedArrayToBSTWorker(nums, mid + 1, high);
		return node;

	}
}
