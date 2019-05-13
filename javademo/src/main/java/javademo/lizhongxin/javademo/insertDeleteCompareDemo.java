package javademo.lizhongxin.javademo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class insertDeleteCompareDemo {

  static List<Object> list = new ArrayList<Object>();
  static Stack<Object> stack = new Stack<Object>();

  public static void main(String[] a) {
    int max = 20000000;
    long start = System.currentTimeMillis();
    for (int i = 0; i < max; i++) {
      list.add(i);
    }
    System.out.println("List耗时：" + (System.currentTimeMillis() - start)+",max:"+max);
    //
    start = System.currentTimeMillis();
    for (int i = 0; i < max; i++) {
      stack.add(i);
    }
    System.out.println("Stack耗时：" + (System.currentTimeMillis() - start));
  }
}
