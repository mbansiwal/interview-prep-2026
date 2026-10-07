package com.interview.blind75.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) cur = cur.next = new ListNode(v);
        return dummy.next;
    }

    public int[] toArray() {
        List<Integer> list = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) { list.add(cur.val); cur = cur.next; }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
