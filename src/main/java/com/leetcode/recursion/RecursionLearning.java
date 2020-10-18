package com.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.datastructures.ListNode;
import com.leetcode.datastructures.TreeNode;

public class RecursionLearning {

	public static void printReverse(char[] str) {
		printReverseWorker(str, str.length - 1);
	}

	private static void printReverseWorker(char[] str, int i) {
		if (i < 0)
			return;
		System.out.print(str[i]);
		printReverseWorker(str, i - 1);
	}

	public static void reverseString(char[] s) {
		System.out.println("Begin");
		printArray(s);
		reverseStringWorker(s, 0, s.length - 1);
		System.out.println("End");
		printArray(s);
	}

	static void reverseStringWorker(char[] s, int begin, int end) {
		if (begin < end) {
			char temp = s[begin];
			s[begin] = s[end];
			s[end] = temp;
			// begin++; end--;
			reverseStringWorker(s, begin + 1, end - 1);
		}
	}

	static void printArray(char[] s) {
		for (char c : s)
			System.out.print(c);

	}

	// f(i,j)=f(i−1,j−1)+f(i−1,j)
	public static List<List<Integer>> pascalTriangle(int numRows) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return answer;

		answer.add(new ArrayList<>());
		answer.get(0).add(1);

		for (int i = 1; i < numRows; i++) {
			answer.add(new ArrayList<>());
			answer.get(i).add(1);
			for (int j = 1; j < i; j++) {
				int leftUp = answer.get(i - 1).get(j - 1);
				int rightUp = answer.get(i - 1).get(j);
				answer.get(i).add(leftUp + rightUp);
			}
			answer.get(i).add(1);
		}
		return answer;
	}

	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return null;
		if (root.val == val)
			return root;
		if (root.val > val)
			return searchBST(root.left, val);
		else
			return searchBST(root.right, val);
	}

	public static List<Integer> pascalTriangle2(int rowIndex) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		triangle.add(new ArrayList<Integer>());
		triangle.get(0).add(1);
		for (int i = 1; i <= rowIndex; i++) {
			ArrayList<Integer> row = new ArrayList<>();
			row.add(1);
			for (int j = 1; j < i; j++) {
				row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
			}
			row.add(1);
			triangle.add(row);
		}
		return triangle.get(rowIndex);
	}

	public static int fib(int n) {
		if (n < 2)
			return n;
		int[] memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;

		return fibWorker(n, memo);
	}

	static int fibWorker(int n, int[] memo) {

		if (n < 2)
			return memo[n];
		if (memo[n] == 0) {
			memo[n] = fibWorker(n - 1, memo) + fibWorker(n - 2, memo);
		}

		return memo[n];
	}

	// 1| 1| 2| 3|
	// topdown
	public static int climbStairs(int n) {
		if (n < 1)
			return -1;
		int[] memo = new int[n + 1];
		memo[0] = 1;
		memo[1] = 1;
		return climbStairsWorker(n, memo);
	}

	static int climbStairsWorker(int n, int[] memo) {
		if (n < 0)
			return 0;
		if (memo[n] == 0)
			memo[n] = climbStairsWorker(n - 1, memo) + climbStairsWorker(n - 2, memo);
		return memo[n];
	}

	// bottom up
	public static int climbStairsIterOld(int n) {
		if (n == 1)
			return n;
		int[] memo = new int[n + 1];
		memo[1] = 1;
		memo[2] = 2;

		for (int i = 3; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}
		return memo[n];
	}

	public static int climbStairsIter(int n) {
		if (n < 1)
			return -1;
		int twoBack = 1;
		int oneBack = 1;
		for (int i = 2; i <= n; i++) {
			int temp = oneBack + twoBack;
			twoBack = oneBack;
			oneBack = temp;
		}
		return oneBack;
	}

	public static int sum_non_tail_recursion(int[] ls) {
		if (ls == null || ls.length == 0)
			return 0;
		return sum_non_tail_recursion_worker(ls, ls.length - 1);
	}

	static int sum_non_tail_recursion_worker(int[] ls, int index) {
		if (index < 0)
			return 0;
		return sum_non_tail_recursion_worker(ls, index - 1) + ls[index];
	}

	public static int sum_tail_recursion(int[] ls) {
		if (ls == null || ls.length == 0)
			return 0;
		return sum_tail_recursion_worker(ls, 0, 0);
	}

	static int sum_tail_recursion_worker(int[] ls, int index, int sum) {
		if (index >= ls.length)
			return sum;
		return sum_tail_recursion_worker(ls, index + 1, sum + ls[index]);
	}

	// static int maxDepth_max = 0;

	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;

		// return maxDepth_max;
	}

	public static int maxDepth_tail(TreeNode root) {
		if (root == null)
			return 0;
		int sum = 0;
		return maxDepth_tail_worker(root, sum);
	}

	static int maxDepth_tail_worker(TreeNode root, int sum) {
		if (root == null)
			return sum;
		int left = maxDepth_tail_worker(root.left, sum + 1);
		int right = maxDepth_tail_worker(root.right, sum + 1);
		return Math.max(left, right);
	}

//NOt working. See comments in https://leetcode.com/problems/powx-n/discuss/19546/Short-and-easy-to-understand-solution
	public static double myPow(double x, int n) {
		if (n == 0)
			return 1.0;
		if (n == 1)
			return x;
		double result = myPow_worker(x, n, 2);
		return (n > 0) ? result : 1 / result;
	}

	static double myPow_worker(double x, int n, int index) {
		if (index > Math.abs(n))
			return x;
		return myPow_worker(x, n, index + 1) * x;
	}

	public static ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode result = new ListNode(0);
		ListNode head = result;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		head.next = l1 == null ? l2 : l1;
		return result.next;
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val <= l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	public static int kthGrammar(int n, int k) {
		StringBuilder sb = new StringBuilder("01");
		if (n > 2) {
			for (int i = 3; i <= n; i++) {
				int length = sb.length();
				int start = (length / 2);// + 1;
				for (int j = start; j < length; j++) {
					sb.append(sb.charAt(j) == '0' ? "01" : "10");
				}
			}
		}
		return (int) sb.charAt(k - 1) - '0';
	}

	public static int uniquenumBSTrees(int n) {
		int[] array = new int[n + 1];
		array[0] = 1;
		array[1] = 1;
		for (int i = 2; i < array.length; i++) {
			for (int j = 1; j <= i; j++) {
				array[i] += array[j - 1] * array[i - j];
			}
		}
		return array[n];
	}
}
