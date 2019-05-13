package javademo.lizhongxin.javademo.StackAndQueue;

import java.util.Stack;

/**
 * Created by lizhongxin on 05/02/2019.
 */

public class testStack  {

  public static void main(String[] a) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    Integer peek = stack.peek();
    System.out.println("peek:"+peek.intValue());
    Integer pop = stack.pop();
    System.out.println("pop:"+pop.intValue());
    Integer peek2 = stack.peek();
    System.out.println("peek:"+peek2.intValue());
    Integer firstElement = stack.firstElement();
    System.out.println("firstElement:"+firstElement.intValue());
  }
}
