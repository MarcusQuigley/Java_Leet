package com.leetcode.tests.trees;

import com.leetcode.tests.datastructures.TreeNode;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class TreesProblemsEasyTest extends TreeTestsBase {

    static Stream<Arguments> source_rangeSumBST() {
        return Stream.of(arguments(new int[]{1, 5, 7}, 3, 5, 5),
                arguments(new int[]{3, 5, 7, 10, 15}, 7, 15, 32),
                arguments(new int[]{1, 3, 5, 7, 10, 13, 15, 18}, 6, 10, 17)
        );
    }

    @ParameterizedTest
    @MethodSource("source_rangeSumBST")
    void test_rangeSumBST(int[] array, int left, int right, int expected) {
        TreeNode root = createBstTreeNodes(array);
        int actual = TreesProblemsEasy.rangeSumBST(root, left, right);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("source_rangeSumBST")
    void test_rangeSumBSTIter(int[] array, int left, int right, int expected) {
        TreeNode root = createBstTreeNodes(array);
        int actual = TreesProblemsEasy.rangeSumBSTIter(root, left, right);
        assertEquals(expected, actual);
    }

    static Stream<Arguments> source_getLonelyNodes() {
        return Stream.of(arguments(new int[]{1, 2, 3}, new int[]{}),
                arguments(new int[]{3, 5, 7, 10}, new int[]{10}),
                arguments(new int[]{1, 3}, new int[]{3})
        );
    }

    @ParameterizedTest
    @MethodSource("source_getLonelyNodes")
    void test_getLonelyNodes(int[] array, int[] expected) {
        TreeNode root = createTreeNodes(array);
        List<Integer> actual = TreesProblemsEasy.getLonelyNodes(root);
        assertArrayEquals(expected, ArrayListToArray(actual));
    }

    @ParameterizedTest
    @MethodSource("source_getLonelyNodes")
    void test_getLonelyNodesIter(int[] array, int[] expected) {
        TreeNode root = createTreeNodes(array);
        List<Integer> actual = TreesProblemsEasy.getLonelyNodesIter(root);
        assertArrayEquals(expected, ArrayListToArray(actual));
    }

    static Stream<Arguments> source_mergeTrees() {
        return Stream.of(arguments(new int[]{1, 1}, new int[]{2, 2, 2}, new int[]{3, 3, 2}),
                arguments(new int[]{1, 2, 3}, new int[]{2, 2, 2}, new int[]{3, 4, 5}),
                arguments(new int[]{1, 3, 2, 5}, new int[]{3, 6, 1}, new int[]{4, 9, 3, 5})
        );
    }
//    @ParameterizedTest
//    @MethodSource("source_mergeTrees")
//    void test_mergeTrees(int[] array1,int[] array2, int[] expected) {
//        TreeNode root1 = createTreeNodes(array1);
//        TreeNode root2 = createTreeNodes(array2);
//        TreeNode actual = ProblemsEasy.mergeTrees(root1, root2 );
//        assertArrayEquals(expected, TreeToArrayInOrder(actual));
//    }

    //  [InlineData(new int[] { 4, 2, 7, -666, -666, 4 }, 2, new int[] { 2 })]

    static Stream<Arguments> source_searchBST() {
        return Stream.of(arguments(new int[]{3, 2, 7, 1, 3}, 2, new int[]{2, 1, 3})
                , arguments(new int[]{3, 5, 7}, 6, new int[]{})
                , arguments(new int[]{4, 2, 7, -666, -666, 4}, 2, new int[]{2})
        );
    }

    @ParameterizedTest
    @MethodSource("source_searchBST")
    void test_searchBST(int[] array, int val, int[] expected) {
        TreeNode root = createTreeNodes(array);
        TreeNode actual = TreesProblemsEasy.searchBST(root, val);
        int[] listActual = ArrayFromTree(actual);
        assertArrayEquals(expected, listActual);
    }
    @ParameterizedTest
    @MethodSource("source_searchBST")
    void test_searchBSTIter(int[] array, int val, int[] expected) {
        TreeNode root = createTreeNodes(array);
        TreeNode actual = TreesProblemsEasy.searchBSTIter(root, val);
        int[] listActual = ArrayFromTree(actual);
        assertArrayEquals(expected, listActual);
    }
}