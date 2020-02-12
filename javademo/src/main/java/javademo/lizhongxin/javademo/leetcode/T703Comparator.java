package javademo.lizhongxin.javademo.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class T703Comparator {

  public static void main(String[] a) {

    int i = Integer.MAX_VALUE >>> 1;
    System.out.println("i="+i);
    int[] ints = new int[]{3, 1, 2, 9, 4};
    Queue<Integer> q = Q(ints);
    while (q.peek() != null) {
      System.out.println(q.poll() + "");
    }
  }

  //-2^31个位是8,2^31-1个位是7
  public static Queue<Integer> Q(int[] stones) {
    Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        System.out.println("o1=" + o1 + ",o2=" + o2);
//        if (o1>o2){
//          return 1;
//        }else {
//          return -1;
//        }
        return o2 - o1;
      }
    });
    for (int i = 0; i < stones.length; i++) {
      q.offer(stones[i]);
    }
    return q;
  }
}
