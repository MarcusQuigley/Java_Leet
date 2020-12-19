package com.leetcode.scratch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.TreeNode;
import com.leetcode.trees.TreeTestsBase;

public class ScratchTests2 extends TreeTestsBase {

	Scratch2 sut;

	public ScratchTests2() {
		sut = new Scratch2();
	}

	static Stream<Arguments> source_isValidBST() {
		return Stream.of(arguments(new int[] { 2, 1, 3 }, true), arguments(new int[] { 1, 2, 3, -666, 5 }, false),
				arguments(new int[] { 5, 1, 4, -666, -666, 3, 6 }, false), arguments(new int[] { 1, 2 }, false),
				arguments(new int[] { 11, 2 }, true), arguments(new int[] { 1, -666, 1 }, false),
				arguments(new int[] { -2147483648, -2147483648 }, false), arguments(new int[] { -21, -21 }, false),
				arguments(new int[] { -2147483648, }, true),
				arguments(new int[] { -2147483648, -666, 2147483647 }, true),
				arguments(new int[] { 10, 5, 15, -666, -666, 6, 20 }, false)

		);
	}

	@ParameterizedTest
	@MethodSource("source_isValidBST")
	void test_isValidBST(int[] array, boolean expected) {
		TreeNode node = createTreeNodes(array);
		var actual = sut.isValidBST(node);
		assertEquals(expected, actual);
	}

}
