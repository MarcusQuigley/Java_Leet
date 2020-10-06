package com.leetcode.tests.trees;

import com.leetcode.tests.datastructures.TreeNode;
import com.leetcode.trees.ProblemsEasy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class ProblemsEasyTest extends TreeTestsBase {

    static Stream<Arguments> source_rangeSumBST() {
        return Stream.of(arguments(new int[] { 1, 5, 7 }, 3, 5, 5),
                arguments(new int[] {  3,5,7,10, 15 }, 7, 15, 32),
                arguments(new int[] { 1,3,5,7, 10,13,15, 18  }, 6, 10, 17)
        );
    }
    @ParameterizedTest
    @MethodSource("source_rangeSumBST")
    void test_rangeSumBST(int[] array, int left, int right, int expected) {
        TreeNode root = createTreeNodes(array);
        int actual = ProblemsEasy.rangeSumBST(root, left, right);
        assertEquals(expected, actual);
    }
    @ParameterizedTest
    @MethodSource("source_rangeSumBST")
    void test_rangeSumBSTIter(int[] array, int left, int right, int expected) {
        TreeNode root = createTreeNodes(array);
        int actual = ProblemsEasy.rangeSumBSTIter(root, left, right);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> source_getLonelyNodes() {
        return Stream.of(arguments(new int[] { 1,2,3 },  new int[] {}),
                arguments(new int[] {  3,5,7,10 },  new int[]{10}),
                arguments(new int[] { 1,3  } ,new int[] { 3  })
        );
    }
    @ParameterizedTest
    @MethodSource("source_getLonelyNodes")
    void test_getLonelyNodes(int[] array, int[] expected) {
        TreeNode root = createTreeNodes(array);
        List<Integer> actual = ProblemsEasy.getLonelyNodes(root );
        assertArrayEquals(expected, ArrayListToArray(actual));
    }
    @ParameterizedTest
    @MethodSource("source_getLonelyNodes")
    void test_getLonelyNodesIter(int[] array, int[] expected) {
        TreeNode root = createTreeNodes(array);
        List<Integer> actual = ProblemsEasy.getLonelyNodesIter(root );
        assertArrayEquals(expected, ArrayListToArray(actual));
    }

//
}