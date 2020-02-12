package javademo.lizhongxin.javademo.sort;

public class mergesort {

  public static void main(String[] arg) {
    int[] a = new int[]{1, 9, 20, 2, 15, 8};
    mergeSort(a, a.length);
    for (int i = 0; i < a.length; i++) {
      System.out.println("a=" + a[i]);
    }

//    int[] b = new int[]{1, 9, 20, 2, 15, 8};
//    mergeSort(b, b.length);
//    for (int i = 0; i < b.length; i++) {
//      System.out.println("b=" + b[i]);
//    }
  }

  // 归并排序算法, a是数组，n表示数组大小
  public static void mergeSort(int[] a, int n) {
    mergeSortInternally(a, 0, n - 1);
  }

  // 递归调用函数,s是start,e是end
  private static void mergeSortInternally(int[] a, int s, int e) {
    // 递归终止条件
    if (s >= e) {
      return;
    }

    // 取s到e之间的中间位置m,防止（s+e）的和超过int类型最大值
    int m = s + (e - s) / 2;
    // 分治递归
    mergeSortInternally(a, s, m);
    mergeSortInternally(a, m + 1, e);

    // 将A[s...m]和A[m+1...e]合并为A[s...e]
    merge(a, s, m, e);
//    mergeBySentry(a, s, m, e);
  }

  private static void merge(int[] a, int s, int m, int e) {
    int i = s;
    int j = m + 1;
    int k = 0; // 初始化变量i, j, k
    int[] tmp = new int[e - s + 1]; // 申请一个大小跟a[s...e]一样的临时数组
    while (i <= m && j <= e) {
      if (a[i] <= a[j]) {
        tmp[k++] = a[i++]; // i++等于i:=i+1
      } else {
        tmp[k++] = a[j++];
      }
    }

    // 判断哪个子数组中有剩余的数据
    int start = i;
    int end = m;
    if (j <= e) {
      //后半段剩下来
      start = j;
      end = e;
    }

    // 将剩余的数据拷贝到临时数组tmp
    while (start <= end) {
      tmp[k++] = a[start++];
    }

    // 将tmp中的数组拷贝回a[p...r]
    for (i = 0; i <= e - s; ++i) {
      a[s + i] = tmp[i];
    }
  }

  /**
   * 合并(哨兵)
   */
  private static void mergeBySentry(int[] arr, int s, int m, int e) {
    int[] leftArr = new int[m - s + 2];
    int[] rightArr = new int[e - m + 1];

    for (int i = 0; i <= m - s; i++) {
      leftArr[i] = arr[s + i];
    }
    // 第一个数组添加哨兵（最大值）
    leftArr[m - s + 1] = Integer.MAX_VALUE;

    for (int i = 0; i < e - m; i++) {
      rightArr[i] = arr[m + 1 + i];
    }
    // 第二个数组添加哨兵（最大值）
    rightArr[e - m] = Integer.MAX_VALUE;

    int i = 0;
    int j = 0;
    int k = s;
    while (k <= e) {
      // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
      if (leftArr[i] <= rightArr[j]) {
        arr[k++] = leftArr[i++];
      } else {
        arr[k++] = rightArr[j++];
      }
    }
  }

}
