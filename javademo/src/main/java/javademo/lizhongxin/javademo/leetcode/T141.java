package javademo.lizhongxin.javademo.leetcode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javademo.lizhongxin.javademo.linkedlist.ListNode;

public class T141 {

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
    boolean b = hasCycleFastSlow(head);
    System.out.println("isCycle-" + b);

  }

  public static boolean hasCycle(ListNode head) {
    Set<ListNode> set = new HashSet<>();
    while (head != null) {
      if (set.contains(head)) {
        return true;
      }
      set.add(head);
      head = head.next;
    }
    return false;
  }

  public static boolean hasCycleFastSlow(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode fast = head.next;
    ListNode slow = head;
    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return false;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return true;
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
