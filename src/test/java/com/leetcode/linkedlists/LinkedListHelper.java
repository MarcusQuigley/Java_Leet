package com.leetcode.linkedlists;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.datastructures.ListNode;

public class LinkedListHelper {

	public static ListNode CreateLinkedList(int[] values) {
		if (values == null || values.length == 0)
			return null;
		ListNode head = new ListNode(values[0]);
		ListNode current = head;
		for (int i = 1; i < values.length; i++) {
			// while(current.next !=null)
			current.next = new ListNode(values[i]);
			current = current.next;
		}
		return head;
	}

	public static int[] ListFromListNode(ListNode node) {
		List<Integer> vals = new ArrayList<Integer>();
		if (node != null) {
			while (node != null) {
				vals.add(node.val);
				node = node.next;
			}
		}
		return vals.stream().mapToInt(i -> i).toArray();
	}

}
