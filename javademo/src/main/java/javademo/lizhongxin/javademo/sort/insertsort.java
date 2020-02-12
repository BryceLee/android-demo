package javademo.lizhongxin.javademo.sort;

public class insertsort {

  public static void main(String[] arg) {
    int[] a = new int[]{4, 2, 3, 1, 7, 9};
    mmm(a);
//    insertionSort(a, a.length);
  }

  private static void mmm(int[] a) {
    for (int i = 1; i < a.length; ++i) {
      int value = a[i];
      System.out.println("待插入数据：" + value);
      int j = i - 1;
      for (; j >= 0; --j) {
        if (a[j] > value) {
          System.out.println("交换数据：" + (j) + "位置上的" + a[j] + ",插入到" + (j + 1) + "位置上");
          a[j + 1] = a[j];
        } else {
          //找到要插入的位置的前一位
          break;
        }
      }
      a[j + 1] = value;
      System.out.println("插入数据" + value + "到" + (j + 1) + "位置上");
    }
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void insertionSort(int[] a, int n) {
    if (n <= 1) {
      return;
    }

    for (int i = 1; i < n; ++i) {
      int value = a[i];
      int j = i - 1;
      // 查找插入的位置
      for (; j >= 0; --j) {
        if (a[j] > value) {
          a[j + 1] = a[j];  // 数据移动
        } else {
          break;
        }
      }
      a[j + 1] = value; // 插入数据
    }
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }
}
