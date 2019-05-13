package javademo.lizhongxin.mylibrary.testjavademo.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongxin on 10/11/2018.
 */

public class FindArrayOverlapIntervals {

  public static void main(String args[]) {
//    Input: [[1,3],[2,6],[8,10],[15,18]]
//    Output: [[1,6],[8,10],[15,18]]
    List<Interval> intervals = new ArrayList<>();
//    intervals.add(new Interval(1, 4));
//    intervals.add(new Interval(0, 0));
    //*
    intervals.add(new Interval(1, 4));
    intervals.add(new Interval(0, 2));
    intervals.add(new Interval(3, 5));
    //*
//    intervals.add(new Interval(1, 3));
//    intervals.add(new Interval(2, 6));
//    intervals.add(new Interval(8, 10));
//    intervals.add(new Interval(15, 18));
    List<Interval> merge = merge(intervals);
    for (Interval l : merge) {
      System.out.println("[" + l.start + "," + l.end + "]");
    }
  }

  public static List<Interval> merge(List<Interval> intervals) {
    List<Interval> mergeList = new ArrayList<>();
    //数字间两辆比较，如果没有重叠输出自己，输出结果不重复
    for (int i = 0; i < intervals.size(); i++) {
      Interval A = intervals.get(i);//[1,3]
      int start = A.start;
      int end = A.end;
      System.out.println("------------------------");
      System.out.println("-->A[" + A.start + "," + A.end + "]");
      for (int j = i + 1; j < intervals.size(); j++) {
        Interval B = intervals.get(j);
        int startB = B.start;
        int endB = B.end;
        System.out.println("-->B[" + B.start + "," + B.end + "]");
        if (A.start <= B.start && A.end >= B.start) {
//          if (A.end >= B.start) {
          Interval newOne = new Interval(A.start, Math.max(A.end, B.end));
          A = newOne;
          if (!mergeList.contains(newOne)) {
            System.out.println("-->ADD1[" + newOne.start + "," + newOne.end + "]");
            mergeList.add(newOne);
//            } else {
//              System.out.println("-->JUMP1");
//            }
          } else {
            System.out.println("-->JUMP2");
          }
        } else {
          if (A.start <= B.end && B.start <= A.end) {
            Interval newOne = new Interval(B.start, Math.max(A.end, B.end));
            if (!mergeList.contains(newOne)) {
              System.out.println("-->ADD2[" + newOne.start + "," + newOne.end + "]");
              mergeList.add(newOne);
            } else {
              System.out.println("-->JUMP3");
            }
          } else {
            System.out.println("-->JUMP4");
          }
        }
      }
    }
    System.out.println("-->*****");
    return mergeList;
  }

  public static class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }
}
