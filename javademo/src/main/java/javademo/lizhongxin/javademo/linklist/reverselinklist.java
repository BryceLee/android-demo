package javademo.lizhongxin.javademo.linklist;

/**
 * Created by lizhongxin on 03/02/2019.
 */

public class reverselinklist {

  public static void main(String[] a) {
    ListNode head = new ListNode(1);
    ListNode tow = new ListNode(2);
    ListNode three = new ListNode(3);
    ListNode four = new ListNode(4);
    head.setNext(tow);
    tow.setNext(three);
    three.setNext(four);
    ListNode Before = head;
    System.out.println("打印出反转前的数据：");
    while (Before != null) {
      System.out.println(Before.getVal());
      Before = Before.getNext();
    }
    head = reverse(head);
    ListNode after = head;
    System.out.println("打印出反转后的数据:");
    while (after != null) {
      System.out.println(after.getVal());
      after = after.getNext();
    }
  }

  private static ListNode reverse(ListNode head) {
    ListNode cur=head;
    ListNode pre=null;
    while (cur.getNext()!=null){
      ListNode next = cur.getNext();
      //下一个指针
      cur.setNext(pre);
      pre=cur;
      cur=next;
    }
    cur.setNext(pre);
    return cur;
  }
}
