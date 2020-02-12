package javademo.lizhongxin.javademo.leetcode;


import java.util.HashSet;
import java.util.Set;

public class T160 {

  public static void main(String[] s) {

  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Set<ListNode> set = new HashSet<>();
    while (headA != null) {
      set.add(headA);
      headA = headA.next;
    }
    while (headB != null) {
      if (set.contains(headB)) {
        return headB;
      }
      headB = headB.next;
    }
    return null;
  }

  public ListNode getIntersectionNodeTwopointer(ListNode headA, ListNode headB) {
    ListNode p1 = headA, p2 = headB;
    while (p1 != p2) {
      p1 = p1 == null ? headB : p1.next;
      p2 = p2 == null ? headA : p2.next;
    }
    return p1;
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
