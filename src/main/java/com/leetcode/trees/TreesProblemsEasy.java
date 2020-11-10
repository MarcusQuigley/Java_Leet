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

	public int heightBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		var l = heightBinaryTree(root.left);
		var r = heightBinaryTree(root.right);
		return Math.max(l, r) + 1;
	}

	public int heightBinaryTreeIter(TreeNode root) {
		if (root == null)
			return 0;
		int height = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			var size = q.size();
			for (int i = 0; i < size; i++) {
				var current = q.remove();
				if (current.left != null)
					q.add(current.left);
				if (current.right != null)
					q.add(current.right);
			}
			height++;
		}
		return height;
	}

	private class DeepestState {
		public int level;
		public TreeNode node;

		public DeepestState(int level, TreeNode node) {
			this.level = level;
			this.node = node;
		}
	}

	public TreeNode deepestNode(TreeNode root) {
		if (root == null)
			return null;
		DeepestState state = new DeepestState(0, root);
		deepestNodeWorker(root, 0, state);
//		System.out.println("===========");
//		mapdN.forEach((k, v) -> System.out.println("Node " + k + " level " + v));
//		System.out.println("===========");
		return state.node;
	}

	Map<Integer, Integer> mapdN = new HashMap<Integer, Integer>();

	private void deepestNodeWorker(TreeNode node, int level, DeepestState state) {
		mapdN.put(node.val, level);
		if (node.left == null && node.right == null) {
			if (level > state.level) {
				state.level = level;
				state.node = node;
			}
		} else {
			level++;
			if (node.left != null)
				deepestNodeWorker(node.left, level, state);
			if (node.right != null)
				deepestNodeWorker(node.right, level, state);
		}
	}

	public TreeNode deepestNodeIter(TreeNode root) {
		if (root == null)
			return null;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			var current = q.remove();
			if (current.left != null)
				q.add(current.left);
			if (current.right != null)
				q.add(current.right);
			if (q.isEmpty())
				return current;
		}
		return null;
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
		Deque<Entry<TreeNode, Integer>> stack = new ArrayDeque<>();
		stack.push(new SimpleEntry<TreeNode, Integer>(root, root.val));
		while (!stack.isEmpty()) {
			var current = stack.pop();
			var node = current.getKey();
			if (node.left == null && node.right == null)
				sum += current.getValue();
			else {
				if (node.left != null)
					stack.push(new SimpleEntry<TreeNode, Integer>(node.left, current.getValue() << 1 | node.left.val));
				if (node.right != null)
					stack.push(
							new SimpleEntry<TreeNode, Integer>(node.right, current.getValue() << 1 | node.right.val));
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
		Queue<Entry<NaryNode[], Integer>> q = new LinkedList<>();
		q.add(new SimpleEntry<NaryNode[], Integer>(new NaryNode[] { root }, 1));
		while (!q.isEmpty()) {
			var row = q.remove();
			for (var n : row.getKey()) {
				if (n.children.size() > 0)
					q.add(new SimpleEntry<NaryNode[], Integer>(n.children.toArray(new NaryNode[0]),
							row.getValue() + 1));
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
		TreeNode node = new TreeNode(nums[mid]);
		node.left = sortedArrayToBSTWorker(nums, low, mid - 1);
		node.right = sortedArrayToBSTWorker(nums, mid + 1, high);
		return node;
	}

	public static TreeNode sortedArrayToBSTIter(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;

		Deque<Integer> qLow = new ArrayDeque<>();
		Deque<Integer> qHigh = new ArrayDeque<>();
		Deque<TreeNode> q = new ArrayDeque<>();
		TreeNode root = new TreeNode(0);

		qLow.add(0);
		qHigh.add(nums.length - 1);
		q.add(root);
		while (!q.isEmpty()) {
			var l = qLow.remove();
			var h = qHigh.remove();
			int mid = l + (h - l) / 2;
			var node = q.remove();
			node.val = nums[mid];
			if (l <= mid - 1) {
				node.left = new TreeNode(0);
				qLow.add(l);
				qHigh.add(mid - 1);
				q.add(node.left);
			}
			if (h >= mid + 1) {
				node.right = new TreeNode(0);
				qLow.add(mid + 1);
				qHigh.add(h);
				q.add(node.right);
			}
		}
		return root;
	}

	// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
	public static boolean findTargetIter1(TreeNode root, int k) {
		if (root == null)
			return false;

		TreeNode current = root;
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			list.add(current.val);
			current = current.right;
		}
		return findTargetWorker(list, k);
	}

	static boolean findTargetWorker(List<Integer> list, int k) {
		if (list == null || list.size() < 2)
			return false;
		var start = 0;
		var end = list.size() - 1;
		while (start < end) {
			var total = list.get(start) + list.get(end);
			if (total == k)
				return true;
			if (total > k)
				end--;
			else
				start++;
		}
		return false;
	}

	public static boolean findTargetIter2(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			if (q.peek() != null) {
				TreeNode current = q.poll();
				if (set.contains(k - current.val))
					return true;
				set.add(current.val);
				q.add(current.left);
				q.add(current.right);
			} else
				q.poll();
		}
		return false;
	}

	// https://leetcode.com/problems/construct-string-from-binary-tree/
	public static String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}
		if (t.left == null && t.right == null)
			return t.val + "";
		if (t.right == null)
			return "" + t.val + "(" + tree2str(t.left) + ")";
		return "" + t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
	}

	public static String tree2strIter(TreeNode t) {
		if (t == null)
			return "";
		Stack<TreeNode> stack = new Stack<>();
		Set<TreeNode> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		stack.push(t);
		while (!stack.isEmpty()) {
			var current = stack.peek();
			if (set.contains(current)) {
				stack.pop();
				sb.append(")");
			} else {
				set.add(current);
				sb.append("(" + current.val);
				if (current.left == null && current.right != null) {
					sb.append("()");
					stack.push(current.right);
				} else {
					if (current.right != null)
						stack.push(current.right);
					if (current.left != null)
						stack.push(current.left);
				}
			}
		}
		return sb.substring(1, sb.length() - 1);
	}

	// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root != null) {
			Deque<TreeNode> q = new ArrayDeque<>();
			Stack<List<Integer>> stack = new Stack<>();
			q.add(root);
			while (!q.isEmpty()) {
				var count = q.size();
				List<Integer> list = new ArrayList<Integer>(count);
				for (int i = 0; i < count; i++) {
					var node = q.poll();
					list.add(node.val);
					if (node.left != null)
						q.add(node.left);
					if (node.right != null)
						q.add(node.right);
				}
				stack.push(list);

			}
			while (!stack.isEmpty())
				result.add(stack.pop());
		}
		return result;
	}

	// https://leetcode.com/problems/same-tree/
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;

		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public boolean isSameTreeIter(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(p);
		queue.add(q);
		while (!queue.isEmpty()) {
			var left = queue.poll();
			var right = queue.poll();
			if (left == null && right == null)
				continue;
			if (left == null || right == null || left.val != right.val)
				return false;
			queue.add(left.left);
			queue.add(right.left);
			queue.add(left.right);
			queue.add(right.right);
		}
		return true;
	}

	// https://leetcode.com/problems/binary-tree-paths/
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		if (root != null)
			binaryTreePathsWorker(root, result, new StringBuilder()); // "");
		return result;

	}

	static void binaryTreePathsWorker(TreeNode root, List<String> result, StringBuilder sb) {
		if (root == null)
			return;
		var len = sb.length();
		// s = s + root.val + "->";
		sb.append(root.val);

		if (root.left == null && root.right == null)
			result.add(sb.toString());// s.substring(0, s.length() - 2));
		else {
			sb.append("->");
			binaryTreePathsWorker(root.left, result, sb);
			binaryTreePathsWorker(root.right, result, sb);
		}
		sb.setLength(len);
	}

	public static List<String> binaryTreePathsIter(TreeNode root) {
		List<String> result = new ArrayList<>();
		if (root == null)
			return result;
		StringBuilder sb = new StringBuilder();
		Stack<Entry<TreeNode, Integer>> stack = new Stack<>();
		stack.push(new SimpleEntry<>(root, 0));
		while (!stack.isEmpty()) {
			var curKvp = stack.pop();
			var current = curKvp.getKey();
			sb.setLength(curKvp.getValue());
			sb.append(current.val);

			if (current.left == null && current.right == null) {
				result.add(sb.toString());

			} else {
				sb.append("->");
				var len = sb.length();
				if (current.right != null)
					stack.push(new SimpleEntry<>(current.right, len));
				if (current.left != null)
					stack.push(new SimpleEntry<>(current.left, len));
			}
		}
		return result;
	}

	// https://leetcode.com/problems/minimum-absolute-difference-in-bst/
	public static int getMinimumDifferenceIter(TreeNode root) {
		if (root == null)
			return 0;
		int min = Integer.MAX_VALUE;
		Integer prev = null;
		// boolean poppedTwice = false;
		Stack<TreeNode> stack = new Stack<>();
		var current = root;
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (prev != null) {
				min = Math.min(min, Math.abs(prev - current.val));
			}
			prev = current.val;
			current = current.right;
		}
		return min;
	}

	static int min_gmd = Integer.MAX_VALUE;
	static Integer prev_gmd = null;

	public static int getMinimumDifference(TreeNode root) {
		if (root == null)
			return 0;
		getMinimumDifferenceWorker(root);
		return min_gmd;
	}

	static void getMinimumDifferenceWorker(TreeNode root) {
		if (root == null)
			return;

		getMinimumDifferenceWorker(root.left);
		if (prev_gmd != null)
			min_gmd = Math.min(min_gmd, Math.abs(root.val - prev_gmd));
		prev_gmd = root.val;

		getMinimumDifferenceWorker(root.right);
	}

	// https://leetcode.com/problems/sum-of-left-leaves/
	public static int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;

		int l = sumOfLeftLeaves(root.left);
		int r = sumOfLeftLeaves(root.right);
		return l + r + (root.left != null && root.left.left == null && root.left.right == null ? root.left.val : 0);
	}

	public static int sumOfLeftLeavesIter(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
//		Stack<Entry<TreeNode, Boolean>> stack = new Stack<>();
//		stack.push(new SimpleEntry<>(root, false));
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			var current = stack.pop();
			if (current.left != null && current.left.left == null && current.left.right == null)
				// if (curKvp.getValue() == true && current.left == null && current.right ==
				// null)
				sum += current.left.val;
			if (current.left != null)
				stack.push(current.left);
			if (current.right != null)
				stack.push(current.right);
		}
		return sum;
	}

	// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;
		if ((p.val <= root.val && q.val >= root.val) || (p.val >= root.val && q.val <= root.val))
			return root;
		if (p.val <= root.val)
			return lowestCommonAncestor(root.left, p, q);
		else
			return lowestCommonAncestor(root.right, p, q);
	}

	public static TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			var current = stack.pop();
			if (p.val > current.val && q.val > current.val)
				stack.push(current.right);

			if (p.val < current.val && q.val < current.val)
				stack.push(current.left);
			else
				return current;

		}

		return null;
	}

	// https://leetcode.com/problems/closest-binary-search-tree-value/
	// static int closestCV=0;
	public static int closestValueIter(TreeNode root, double target) {
		if (root == null)
			return 0;
		Stack<TreeNode> stack = new Stack<>();
		var current = root;
		double minDiff = Double.MAX_VALUE;
		int result = 0;
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (Math.abs(target - (current.val * 1.0D)) < minDiff) {
				minDiff = Math.abs(target - (current.val * 1.0D));
				result = current.val;
			}
			current = current.right;
		}
		return result;
	}

	// static int max_dobt;

	public static int diameterOfBinaryTree(TreeNode root) {
		int[] ans = new int[] { Integer.MIN_VALUE };
		diameterOfBinaryTreeWorker(root, ans);
		return ans[0];
	}

	static int diameterOfBinaryTreeWorker(TreeNode root, int[] maxValues) {
		if (root == null)
			return 0;
		var l = diameterOfBinaryTreeWorker(root.left, maxValues);
		var r = diameterOfBinaryTreeWorker(root.right, maxValues);
		maxValues[0] = Math.max(maxValues[0], l + r);
		return Math.max(l, r) + 1;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetricWorker(root.left, root.right);
	}

	private boolean isSymmetricWorker(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		var l = isSymmetricWorker(left.left, right.right);
		var r = isSymmetricWorker(left.right, right.left);
		return l && r && left.val == right.val;
	}

	public boolean isSymmetricIter(TreeNode root) {
		if (root == null)
			return true;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root.left);
		q.add(root.right);
		while (!q.isEmpty()) {
			var l = q.poll();
			var r = q.poll();
			if (l == null && r == null)
				continue;
			if (l == null || r == null || l.val != r.val)
				return false;
			q.add(l.left);
			q.add(r.right);
			q.add(l.right);
			q.add(r.left);
		}
		return true;
	}

	// https://leetcode.com/problems/subtree-of-another-tree/
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null)
			return false;
		if (isSubtreeWorker(s, t))
			return true;

		return isSubtree(s.left, t.left) || isSubtree(s.right, t.right);
	}

	boolean isSubtreeWorker(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null || t2 == null)
			return false;
		if (t1.val != t2.val)
			return false;
		return isSubtreeWorker(t1.left, t2.left) && isSubtreeWorker(t1.right, t2.right);
	}

	// https://leetcode.com/problems/path-sum/
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		sum -= root.val;
		if (root.left == null && root.right == null && sum == 0)
			return true;
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

	// https://leetcode.com/problems/cousins-in-binary-tree/
	public boolean isCousinsIter(TreeNode root, int x, int y) {
		if (root == null)
			return false;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			var size = q.size();
			boolean isSiblings = false; // true when current = null and startCheck=true
			boolean isCousins = false;// true when we find first val
			for (int i = 0; i < size; i++) {
				TreeNode current = q.remove();
				if (current == null)
					isSiblings = false;
				else {
					if (current.val == x || current.val == y) {
						if (isCousins == true)
							return (isSiblings == false);
						else
							isCousins = true;
						isSiblings = true;
					}
					if (current.left != null)
						q.add(current.left);
					if (current.right != null)
						q.add(current.right);
					q.add(null);
				}
			}
			if (isCousins)
				return false;
		}
		return false;
	}

	int xLeveliC = 0;
	int yLeveliC = 0;
	Integer xParentiC = null;
	Integer yParentiC = null;

	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null)
			return false;
		isCousinsWorker(root, x, y, 0, null);
		return (xLeveliC == yLeveliC && xParentiC != yParentiC);
	}

	void isCousinsWorker(TreeNode node, int x, int y, int level, Integer parentVal) {
		if (node == null)
			return;
		if (node.val == x) {
			xLeveliC = level;
			xParentiC = parentVal;
		} else if (node.val == y) {
			yLeveliC = level;
			yParentiC = parentVal;
		}
		isCousinsWorker(node.left, x, y, level + 1, node.val);
		isCousinsWorker(node.right, x, y, level + 1, node.val);

	}
}
