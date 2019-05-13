package javademo.lizhongxin.javademo.StackAndQueue;

import java.util.Stack;

public class implementQueueUsingStacks {


  public static void main(String[] a) {
    MyQueue queue = new MyQueue();

    queue.push(1);
    queue.push(2);
    int peek = queue.peek();// 返回 1

    int pop = queue.pop();// 返回 1
    boolean empty = queue.empty();// 返回 false
    System.out.println("" + peek);
    System.out.println("" + pop);
    System.out.println("" + empty);

  }

  /**
   * Initialize your data structure here.
   */
  static class MyQueue {

    private Stack mStackInput = null;
    private Stack mStackOutput = null;

    public MyQueue() {
      mStackInput = new Stack();
      mStackOutput = new Stack();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
      mStackInput.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
      if (mStackOutput.size() == 0) {
        while (mStackInput.size() != 0) {
          int pop = (int) mStackInput.pop();
          mStackOutput.push(pop);
        }
      }
      return (int) mStackOutput.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
      if (mStackOutput.size() == 0) {
        while (mStackInput.size() != 0) {
          int pop = (int) mStackInput.pop();
          mStackOutput.push(pop);
        }
      }
      return (int) mStackOutput.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
      return mStackInput.isEmpty() && mStackOutput.isEmpty();
    }
  }
}
