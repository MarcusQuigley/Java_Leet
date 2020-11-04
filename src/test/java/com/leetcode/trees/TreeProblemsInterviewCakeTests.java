package com.leetcode.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.leetcode.datastructures.TreeNode;

public class TreeProblemsInterviewCakeTests extends TreeTestsBase {

	TreeProblemsInterviewCake sut;

	public TreeProblemsInterviewCakeTests() {
		sut = new TreeProblemsInterviewCake();
	}

	static Stream<Arguments> source_test_IsSuperBalanced() {
		return Stream.of(arguments(new int[] { 1, 5, 7, 4, 76 }, true),
				arguments(new int[] { 1, 3, 5, 7, -666, -666, -666, 9 }, false));
	}

	@ParameterizedTest
	@MethodSource("source_test_IsSuperBalanced")
	void test_IsSuperBalanced(int[] array, boolean expected) {
		TreeNode root = super.createTreeNodes(array);
		boolean actual = sut.IsSuperBalanced(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_test_ValidBSTIter() {
		return Stream.of(arguments(new int[] { 1 }, true), arguments(new int[] { 10, 3, 15, -666, 7, -666, 19 }, true),
				arguments(new int[] { 10, 3, 15, -666, 17, -666, 19 }, false),
				arguments(new int[] { 10, 23, 15, -666, 17 }, false));
	}

	@ParameterizedTest
	@MethodSource("source_test_ValidBSTIter")
	void test_ValidBST(int[] array, boolean expected) {
		TreeNode root = super.createTreeNodes(array);
		boolean actual = sut.validBST(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_test_ValidBSTIter")
	void test_ValidBSTIter(int[] array, boolean expected) {
		TreeNode root = super.createTreeNodes(array);
		boolean actual = sut.validBSTIter(root);
		assertEquals(expected, actual);
	}

	static Stream<Arguments> source_test_kthElementIter() {
		return Stream.of(arguments(new int[] { 1, 2, 3, 4, 5 }, 2, 2), arguments(new int[] { 3, 1, 4, -666, 2 }, 1, 1),
				arguments(new int[] { 5, 3, 6, 2, 4, -666, -666, 1 }, 1, 1)
//				arguments(new int[] { 10, 23, 15, -666, 17 }, false)

		);
	}

	@ParameterizedTest
	@MethodSource("source_test_ValidBSTIter")
	void test_ValidBST(int[] array, int k, int expected) {
		TreeNode root = super.createTreeNodes(array);
		boolean actual = sut.validBST(root);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@MethodSource("source_test_kthElementIter")
	void test_kthElementIter(int[] array, int k, int expected) {
		TreeNode root = super.createTreeNodes(array);
		int actual = sut.kthElementIter(root, k);
		assertEquals(expected, actual);
	}

}
