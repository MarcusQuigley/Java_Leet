package com.leetcode.arrays;

public class ArraysProblemsEasy {
	// https://leetcode.com/problems/fibonacci-number/
	public static int fibTopDown(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return fibTopDown(N - 1) + fibTopDown(N - 2);
	}

	public static int fibMemoTopDown(int N) {
		return fibMemoTopDownWorker(N, new int[N + 1]);
	}

	static int fibMemoTopDownWorker(int n, int[] memo) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (memo[n] == 0)
			memo[n] = fibMemoTopDownWorker(n - 1, memo) + fibMemoTopDownWorker(n - 2, memo);
		return memo[n];
	}

	public static int fibMemoBottomUp(int n) {
		if (n < 2)
			return n;
		int[] memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;
		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}
		return memo[n];
	}

	public static int fibBottomUpNoMemo(int n) {
		if (n < 2)
			return n;
		int twoBack = 0;
		int oneBack = 1;
		for (int i = 2; i < n; i++) {
			int temp = twoBack + oneBack;
			twoBack = oneBack;
			oneBack = temp;
		}
		return twoBack + oneBack;
	}

	// https://leetcode.com/problems/move-zeroes/
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 1)
			return;
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0)
				nums[index++] = nums[i];
		}
		while (index < nums.length)
			nums[index++] = 0;

	}

	public int[] sortArrayByParity(int[] A) {
		if (A == null || A.length < 2)
			return A;
		int pointer = 0;
		int end = A.length - 1;
		int temp = 0;
		while (pointer < end) {// A.length) {
			if (A[pointer] % 2 != 0) {
				temp = A[pointer];
				A[pointer] = A[end];
				A[end] = temp;
				end--;
			} else {
				pointer++;
			}
		}
		return A;
	}

	// [-4,-1,0,3,10]
	// [-7,-3,2,3,11]
	// [-3,-3,2]
	// https://leetcode.com/problems/squares-of-a-sorted-array/
	public int[] sortedSquares(int[] A) {
		if (A == null || A.length < 1)
			return A;

		int[] result = new int[A.length];
		int begin = 0;
		int end = A.length - 1;
		int resultIndex = end;

		while (begin <= end) {
			if (Math.abs(A[begin]) > Math.abs(A[end])) {
				result[resultIndex] = Math.abs(A[begin] * A[begin]);
				begin++;
			} else {
				result[resultIndex] = Math.abs(A[end] * A[end]);
				end--;
			}
			resultIndex--;
		}
		return result;
	}
}
