package javademo.lizhongxin.mylibrary.testjavademo.sort;

/**
 * Created by lizhongxin on 03/11/2018.
 */

public class Bubblesort {

  public static void main(String args[]) {
    //冒泡排序法
    int[] ints = {1, 3, 2, 4, 2, 6};
    getBubbleSort(ints);
  }

  private static void getBubbleSort(int[] ints) {
    //按从小到大顺序排列这些数字
    for (int i = 0; i < ints.length; i++) {
      int curMin = ints[i];
      for (int j = i + 1; j < ints.length; j++) {
        int next = ints[j];
        if (curMin < next) {
          //交换位置
          ints[i] = next;
          ints[j] = curMin;
          curMin = next;
        }
      }
    }
    for (int i = 0; i < ints.length; i++) {
      System.out.println(ints[i]);
    }
  }
}
