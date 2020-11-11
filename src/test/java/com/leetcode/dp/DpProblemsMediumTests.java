package com.leetcode.dp;

//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

//import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//import com.leetcode.datastructures.TreeNode;

public class DpProblemsMediumTests {

	DpProblemsMedium sut;

	public DpProblemsMediumTests() {
		sut = new DpProblemsMedium();
	}

	static Stream<Arguments> source_minPathSum() {
		return Stream.of(arguments(7));
	}

	static Stream<Arguments> source_minPathSum1() {
		return Stream.of(arguments((Object) new int[][] { {} }), arguments((Object) new int[][] { { 1, 5, 1 } }),
				arguments((Object) new int[][] { { 4, 2, 1 } }));
	}

	@ParameterizedTest
	@MethodSource("source_minPathSum")
	void test_minPathSum(int expected) {
		// TreeNode root = this.createTreeNodes(array);

		int[][] array = new int[3][];
		array[0] = new int[] { 1, 3, 1 };
		array[1] = new int[] { 1, 5, 1 };
		array[2] = new int[] { 4, 2, 1 };
		int actual = sut.minPathSum(array);
		assertEquals(expected, actual);
	}

}
