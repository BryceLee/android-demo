package javademo.lizhongxin.javademo.StackAndQueue;

import java.util.LinkedList;

public class implementStackUseQueues {


  public static void main(String[] a) {
    MyStack stack = new MyStack();//FILO
//    stack.push(1);
//    stack.push(2);
//    int top = stack.top();// returns 2
//    int pop = stack.pop();// returns 2
//    boolean empty = stack.empty();// returns false
//    System.out.println("" + top);
//    System.out.println("" + pop);
//    System.out.println("" + empty);

    stack.push(1);
    stack.push(2);
    int top = stack.top();
    int pop = stack.pop();
    stack.push(3);
    int top1 = stack.top();
//    System.out.println("" + top);
//    System.out.println("" + pop);
    System.out.println("" + top1);
  }

  /**
   * Initialize your data structure here.
   */
  static class MyStack {

    private LinkedList mQueueInput = null;

    public MyStack() {
      mQueueInput = new LinkedList();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
      mQueueInput.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
      return (int) mQueueInput.removeFirst();
    }

    /**
     * Get the front element.
     */
    public int top() {
      return (int) mQueueInput.getFirst();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
      return mQueueInput.isEmpty();
    }
  }
}
