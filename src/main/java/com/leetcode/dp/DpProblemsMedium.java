package com.leetcode.dp;

public class DpProblemsMedium {
	// https://leetcode.com/problems/minimum-path-sum/
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return -1;

		int rowLength = grid.length;
		int colLength = grid[0].length;
		for (int r = 0; r < rowLength; r++) {
			for (int c = 0; c < colLength; c++) {
				if (r == 0 && c == 0)
					continue;
				else if (r == 0)
					grid[r][c] = grid[r][c] + grid[r][c - 1];
				else if (c == 0)
					grid[r][c] = grid[r][c] + grid[r - 1][c];
				else
					grid[r][c] = grid[r][c] + Math.min(grid[r - 1][c], grid[r][c - 1]);
			}
		}
		return grid[rowLength - 1][colLength - 1];
	}
}
