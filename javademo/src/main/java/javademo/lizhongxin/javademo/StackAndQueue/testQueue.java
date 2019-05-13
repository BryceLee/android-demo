package javademo.lizhongxin.javademo.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by lizhongxin on 05/02/2019.
 */

public class testQueue {

  public static void main(String[] a) {
    Queue queue = new LinkedList();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);

    Integer peek1 = (Integer) queue.peek();
    System.out.println("peek:"+peek1.intValue());

    Integer peek = (Integer) ((LinkedList) queue).removeFirst();
    System.out.println("pop:"+peek.intValue());

    Integer peek2 = (Integer) queue.peek();
    System.out.println("peek:"+peek2.intValue());
  }
}
