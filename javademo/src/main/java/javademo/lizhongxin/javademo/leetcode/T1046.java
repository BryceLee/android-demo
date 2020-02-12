package javademo.lizhongxin.javademo.leetcode;

import android.os.Build.VERSION_CODES;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class T1046 {

  public static void main(String[] a) {
    int[] ints = new int[]{3,1,2,9,4};
    int i = lastStoneWeight(ints);
  }

  //-2^31个位是8,2^31-1个位是7
  public static int lastStoneWeight(int[] stones) {
    Queue<Integer> q=new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2-o1;
      }
    });
    for(int i=0;i<stones.length;i++){
      q.offer(stones[i]);
    }
    while(q.size()>1){
      int y=q.poll();
      int x=q.poll();
      if(x!=y){
        q.offer(y-x);
      }
    }
    if(q.size()==0){
      return 0;
    }
    return q.peek();
  }
}
