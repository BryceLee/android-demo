package javademo.lizhongxin.javademo.leetcode;


import static javademo.lizhongxin.javademo.SynchronizedDeadDemo.b;

import java.util.HashSet;
import java.util.Set;

public class T142 {

  public static void main(String[] s) {
    ListNode head = new ListNode(
        1);
    ListNode tow = new ListNode(
        2);
    ListNode three = new ListNode(
        3);
    ListNode four = new ListNode(
        1);
    head.setNext(tow);
    tow.setNext(three);
    three.setNext(four);
    four.next = head;
    ListNode listNode = hasCycleTwoPointer(head);
    System.out.println("isCycle-" + listNode != null);

  }

  public static ListNode hasCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    while (head != null) {
      if (set.contains(head)) {
        return head;
      }
      set.add(head);
      head = head.next;
    }
    return null;
  }

  public static ListNode hasCycleTwoPointer(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    //构造第一次相遇
    while (true) {
      if (fast == null || fast.next == null) {
        return null;
      }
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }
    fast = head;
    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }
    return fast;
  }

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }

    public ListNode getNext() {
      return next;
    }

    public void setNext(ListNode next) {
      this.next = next;
    }
  }
}
