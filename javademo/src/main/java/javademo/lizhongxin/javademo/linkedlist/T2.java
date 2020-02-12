package javademo.lizhongxin.javademo.linkedlist;

public class T2 {

  public static void main(String[] a) {
    ListNode head = new ListNode(1);
    ListNode tow = new ListNode(2);
    ListNode three = new ListNode(3);
    ListNode four = new ListNode(4);
    head.setNext(tow);
    tow.setNext(three);
    three.setNext(four);
    ListNode node = addTwoNumbers(head, tow);
    while (node.next != null) {
      System.out.print("" + node.val);
      node = node.next;
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode n1 = l1;
    ListNode n2 = l2;
    ListNode headnode = new ListNode(0);
    ListNode currentNode = headnode;
    int carry = 0;
    int currentValue;
    while (n1 != null || n2 != null) {
      int sum = (n1 != null ? n1.val : 0) + (n2 != null ? n2.val : 0) + carry;
      if (sum > 9) {
        carry = sum / 10;
      } else {
        carry = 0;
      }
      currentValue = sum % 10;//确认当前值
      currentNode.next = new ListNode(currentValue);
      currentNode = currentNode.next;//移动位置
      if (n1 != null) {
        n1 = n1.next;
      }
      if (n2 != null) {
        n2 = n2.next;
      }
    }
    if (carry > 0) {
      currentNode.next = new ListNode(carry);
    }
    return headnode.next;
  }

}
