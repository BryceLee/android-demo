package javademo.lizhongxin.javademo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class T703 {

  class KthLargest {

    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
      this.k = k;
      queue = new PriorityQueue<>(k);
      for (Integer i : nums) {
        add(i);
      }
    }

    public int add(int val) {
      if (queue.size() < k) {
        queue.offer(val);
      } else {
        if (val > queue.peek()) {
          queue.poll();
          queue.offer(val);
        }
      }
      return queue.peek();
    }
  }
}
