package com.leetcode.recursion;

import com.leetcode.datastructures.TreeNode;
import com.leetcode.trees.TreeTestsBase;
import com.leetcode.trees.TreesProblemsEasy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RecursionLearningTest extends TreeTestsBase {
    static Stream<Arguments> source_rangeSumBST() {
        return Stream.of(arguments(new int[]{4,2, 7,1,3}, 2, new int[]{2,1,3})
        );
    }

    @ParameterizedTest
    @MethodSource("source_rangeSumBST")
    void test_rangeSumBST(int[] array, int val, int[] expected) {
        TreeNode root = createTreeNodes(array);
        TreeNode actual = RecursionLearning.searchBST(root, val);
        assertArrayEquals(expected, ArrayFromTree(actual));
    }

    static Stream<Arguments> source_pascalTriangle2() {
        return Stream.of(arguments(2, new int[]{1, 2, 1}),
                arguments(3, new int[]{1, 3,3, 1}),
                arguments(1, new int[]{1,  1}),
                arguments(4, new int[]{1, 4,6,4, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("source_pascalTriangle2")
    void test_pascalTriangle2(int rowIndex, int[] expected) {
        List<Integer> actual = RecursionLearning.pascalTriangle2(rowIndex);
        assertArrayEquals(expected, ArrayListToArray(actual));
    }
}
