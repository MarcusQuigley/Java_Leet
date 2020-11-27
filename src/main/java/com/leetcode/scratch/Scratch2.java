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

public class Scratch2 {

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
}
