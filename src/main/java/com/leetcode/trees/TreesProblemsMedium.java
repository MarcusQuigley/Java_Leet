package com.leetcode.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;

import java.util.Set;
import java.util.Stack;

import com.leetcode.datastructures.TreeNode;
import com.leetcode.datastructures.NaryNode;

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

	int posVBst = 0;

	public TreeNode bstFromPreorder(int[] preorder) {
		return bstFromPreorderWorker(null, null, preorder);
	}

	TreeNode bstFromPreorderWorker(Integer min, Integer max, int[] preorder) {
		if (posVBst >= preorder.length)
			return null;
		TreeNode newNode = null;
		int currentValue = preorder[posVBst];
		boolean leftValid = (min == null) ? true : currentValue > min;
		boolean rightValid = (max == null) ? true : currentValue < max;
		if (leftValid && rightValid) {
			newNode = new TreeNode(currentValue);
			posVBst++;
			newNode.left = bstFromPreorderWorker(min, currentValue, preorder);
			newNode.right = bstFromPreorderWorker(currentValue, max, preorder);

		}
		return newNode;
	}

	public TreeNode bstFromPreorderIter(int[] preorder) {
		if (preorder == null || preorder.length == 0)
			return null;

		Stack<TreeNode> stack = new Stack<>();
		TreeNode root = new TreeNode(preorder[0]);
		stack.push(root);

		for (int i = 1; i < preorder.length; i++) {
			var newNode = new TreeNode(preorder[i]);
			if (newNode.val < stack.peek().val) {
				stack.peek().left = newNode;
				stack.push(newNode);
			} else {
				TreeNode temp = null;
				while (!stack.isEmpty() && stack.peek().val < newNode.val) {
					temp = stack.pop();
				}
				temp.right = newNode;
				stack.push(newNode);
			}
		}
		return root;
	}

	public boolean isValidBST(TreeNode root) {
		return isValidBSTWorker(root, null, null);
	}

	boolean isValidBSTWorker(TreeNode node, Integer min, Integer max) {
		if (node == null)
			return true;

		if ((min != null && node.val <= min) || (max != null && node.val >= max))
			return false;
		return isValidBSTWorker(node.left, min, node.val) && isValidBSTWorker(node.right, node.val, max);
	}

	// https://leetcode.com/problems/validate-binary-search-tree/
	public boolean isValidBSTIter(TreeNode root) {
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

//https://leetcode.com/problems/deepest-leaves-sum
	int maxLeveldLS = 0;
	int sumdLS = 0;

	public int deepestLeavesSum(TreeNode root) {
		if (root == null)
			return 0;
		deepestLeavesSumWorker(root, 0);
		return sumdLS;
	}

	private void deepestLeavesSumWorker(TreeNode root, int level) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			if (level > maxLeveldLS) {
				maxLeveldLS = level;
				sumdLS = root.val;
			} else if (level == maxLeveldLS)
				sumdLS += root.val;
		}
		level++;
		deepestLeavesSumWorker(root.left, level);
		deepestLeavesSumWorker(root.right, level);

	}

	public int deepestLeavesSumIter(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;

		Deque<TreeNode> q = new ArrayDeque<>();
		q.add(root);
		while (!q.isEmpty()) {
			sum = 0;
			int size = q.size();
			while (size-- > 0) {
				// for (int i = 0; i < size; i++) {
				TreeNode node = q.pop();
				if (node.left == null && node.right == null)
					sum += node.val;
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
		}
		return sum;
	}

	// https://leetcode.com/problems/clone-n-ary-tree/
	public NaryNode cloneTree(NaryNode root) {
		if (root == null)
			return null;
		Map<NaryNode, NaryNode> map = new HashMap<>();
		Queue<NaryNode> q = new LinkedList<>();
		q.offer(root);

		// NaryNode clone = new NaryNode(0);
		while (!q.isEmpty()) {
			var size = q.size();
			while (size-- > 0) {
				var node = q.remove();
				NaryNode cloned = map.getOrDefault(node, new NaryNode(node.val));

				for (NaryNode child : node.children) {
					q.add(child);
					NaryNode clonedChild = map.getOrDefault(child, new NaryNode(child.val));
					cloned.children.add(clonedChild);
					map.put(child, clonedChild);
				}
				map.put(node, cloned);
			}
		}
		return map.get(root);
	}

	public int sumEvenGrandparent(TreeNode root) {
		return sumEvenGrandparentWorker(root, null, null);
	}

	private int sumEvenGrandparentWorker(TreeNode node, TreeNode parent, TreeNode grandparent) {
		if (node == null)
			return 0;
		var sum = 0;
		if (grandparent != null && grandparent.val % 2 == 0)
			sum = node.val;
		return sumEvenGrandparentWorker(node.left, node, parent) + sumEvenGrandparentWorker(node.right, node, parent)
				+ sum;
	}

	public int sumEvenGrandparentIter(TreeNode root) {
		if (root == null)
			return 0;

		var sum = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			var current = q.poll();
			if (current.val % 2 == 0) {
				if (current.left != null) {
					sum += (current.left.left != null) ? current.left.left.val : 0;
					sum += (current.left.right != null) ? current.left.right.val : 0;
					q.offer(current.left);
				}
				if (current.right != null) {
					sum += (current.right.left != null) ? current.right.left.val : 0;
					sum += (current.right.right != null) ? current.right.right.val : 0;
					q.offer(current.right);
				}

			} else {
				if (current.left != null)
					q.offer(current.left);
				if (current.right != null)
					q.offer(current.right);
			}
		}
		return sum;
	}

	// https://leetcode.com/problems/maximum-binary-tree/
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null || nums.length == 0)
			return null;
		Queue<TreeNode> stack = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			TreeNode current = new TreeNode(nums[i]);
			while (!stack.isEmpty() && stack.peek().val < nums[i]) {
				current.left = stack.poll();
			}
			if (!stack.isEmpty() && stack.peek().val > nums[i]) {
				stack.peek().right = current;
			}
			stack.offer(current);
		}
		return stack.poll();
	}

	// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
	public TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p)
			return root;

		Map<TreeNode, TreeNode> map = new HashMap<>();
		Queue<TreeNode> qu = new LinkedList<>();
		map.put(root, null);
		qu.add(root);
		while (!qu.isEmpty() && (!map.containsKey(p) || !map.containsKey(q))) {
			TreeNode current = qu.remove();

			if (current.left != null) {
				map.put(current.left, current);
				qu.offer(current.left);
			}
			if (current.right != null) {
				map.put(current.right, current);
				qu.offer(current.right);
			}
		}
		Set<TreeNode> set = new HashSet<>();
		set.add(p);
		var node = map.get(p);
		while (node != null) {
			set.add(node);
			node = map.get(node);
		}
		var otherNode = map.get(q);
		while (!set.contains(otherNode))
			otherNode = map.get(otherNode);
		return otherNode;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;
		var l = lowestCommonAncestor(root.left, p, q);
		var r = lowestCommonAncestor(root.right, p, q);
		if (l == null && r == null)
			return null;
		if (l != null && r != null)
			return root;
		return l != null ? l : r;
	}

	public TreeNode bstFromPreorderBad(int[] preorder) {
		if (preorder == null || preorder.length == 0)
			return null;

		TreeNode root = new TreeNode(preorder[0]);
		for (int i = 1; i < preorder.length; i++) {

			TreeNode parent = null;
			TreeNode current = root;
			while (current != null) {
				parent = current;
				if (current.val > preorder[i])
					current = current.left;
				else
					current = current.right;
			}
			if (parent.val > preorder[i])
				parent.left = new TreeNode(preorder[i]);// newNode;
			else
				parent.right = new TreeNode(preorder[i]);// newNode;

		}

		return root;
	}

	public List<Integer> getAllElementsIter(TreeNode root1, TreeNode root2) {

		Deque<TreeNode> q1 = new ArrayDeque<>();
		Deque<TreeNode> q2 = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();

		while (root1 != null || !q1.isEmpty() || root2 != null || !q2.isEmpty()) {
			while (root1 != null) {
				q1.push(root1);
				root1 = root1.left;
			}
			while (root2 != null) {
				q2.push(root2);
				root2 = root2.left;
			}

			if (q2.isEmpty() || !q1.isEmpty() && q1.peek().val < q2.peek().val) {
				root1 = q1.pop();
				list.add(root1.val);
				root1 = root1.right;
			} else {
				root2 = q2.pop();
				list.add(root2.val);
				root2 = root2.right;
			}

		}
		return list;
	}

	// https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/
	public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			var size = q.size();
			for (int i = 0; i < size; i++) {
				var node = q.poll();
				if (node == u) {
					if (i + 1 == size)
						return null;
					else
						return q.poll();
				}
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
		}
		return null;
	}

	// https://leetcode.com/problems/insert-into-a-binary-search-tree/
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);

		if (root.val > val)
			root.left = insertIntoBST(root.left, val);
		else
			root.right = insertIntoBST(root.right, val);
		return root;
	}

	public TreeNode insertIntoBSTIter(TreeNode root, int val) {
		if (root == null)
			return new TreeNode(val);
		var current = root;
		while (true) {
			if (current.val < val) {
				if (current.right == null) {
					current.right = new TreeNode(val);
					break;
				} else
					current = current.right;
			} else {
				if (current.left == null) {
					current.left = new TreeNode(val);
					break;
				} else
					current = current.left;
			}
		}
		return root;
	}

	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (root == null)
			return null;
		root.left = removeLeafNodes(root.left, target);
		root.right = removeLeafNodes(root.right, target);
		if (root.left == null && root.right == null && root.val == target)
			return null;
		else {
			return root;
		}

	}

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root != null)
			inorderTraversalWorker(root, list);
		return list;
	}

	void inorderTraversalWorker(TreeNode node, List<Integer> list) {
		if (node == null)
			return;
		inorderTraversalWorker(node.left, list);
		list.add(node.val);
		inorderTraversalWorker(node.right, list);
	}

	public List<Integer> inorderTraversalIter(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		var current = root;
		Deque<TreeNode> stack = new ArrayDeque<>();
		while (!stack.isEmpty() || current != null) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			list.add(current.val);
			current = current.right;
		}
		return list;
	}

	public List<Integer> inorderTraversalIterIntuitive(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Deque<TreeNode> stack = new ArrayDeque<>();
		Set<TreeNode> set = new HashSet<>();
		stack.add(root);
		TreeNode current = null;
		while (!stack.isEmpty()) {
			current = stack.pop();
			if (set.contains(current))
				list.add(current.val);
			else {
				set.add(current);
				if (current.right != null)
					stack.push(current.right);
				stack.push(current);
				if (current.left != null)
					stack.push(current.left);

			}
		}
		return list;
	}

	public List<Integer> postOrderTraversalIntuitive2(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		HashSet<TreeNode> set = new HashSet<TreeNode>();
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			var current = stack.pop();
			if (set.contains(current))
				list.add(current.val);
			else {
				set.add(current);
				stack.push(current);
				if (current.right != null)
					stack.push(current.right);
				if (current.left != null)
					stack.push(current.left);
			}
		}
		return list;
	}

	public List<Integer> postOrderTraversalWithPrevNode(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;

		TreeNode prev = null;
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			var current = stack.peek();
			// going down
			if (prev == null || prev.left == current || prev.right == current) {
				if (current.left != null) {
					stack.push(current.left);
				} else if (current.right != null) {
					stack.push(current.right);
				} else {
					list.add(stack.pop().val);
				}
			}

			// going up from left
			else if (current.left == prev) {
				if (current.right != null)
					stack.push(current.right);
				else
					list.add(stack.pop().val);
				// going up from right
			} else if (current.right == prev) {
				list.add(stack.pop().val);
			}

			prev = current;
		}
		return list;
	}

	public List<Integer> postOrderTraversalIntuitive(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		Set<TreeNode> set = new HashSet<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			var current = stack.pop();
			if (set.contains(current))
				list.add(current.val);
			else {
				set.add(current);
				if (current.right != null)
					stack.push(current.right);
				stack.push(current);
				if (current.left != null)
					stack.push(current.left);
			}
		}
		return list;
	}

	int preorderIndex = 0;
	HashMap<Integer, Integer> map = new HashMap<>();

	// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || inorder == null || preorder.length != inorder.length)
			return null;
		int pre = 0;

		for (int i : inorder) {

			map.put(i, pre++);
		}

		return buildTreeWorker(preorder, inorder, 0, preorder.length - 1);
	}

	TreeNode buildTreeWorker(int[] preorder, int[] inorder, int startIndex, int endIndex) {
		if (preorderIndex >= preorder.length)
			return null;
		// int inorderIndex = map2[preorder[preorderIndex]];
		int inorderIndex = map.get(preorder[preorderIndex]);
//		for (inorderIndex = startIndex; inorderIndex <= endIndex; inorderIndex++) {
//			if (inorder[inorderIndex] == preorder[preorderIndex]) {
//				break;
//			}
//		}

		TreeNode node = new TreeNode(inorder[inorderIndex]);
		if (inorderIndex > startIndex) {
			preorderIndex++;
			node.left = buildTreeWorker(preorder, inorder, startIndex, inorderIndex - 1);
		}
		if (inorderIndex < endIndex) {
			preorderIndex++;
			node.right = buildTreeWorker(preorder, inorder, inorderIndex + 1, endIndex);
		}
		return node;
	}
}