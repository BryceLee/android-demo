package javademo.lizhongxin.javademo.twodivider;

public class testTwoDivider {

  public static void main(String[] a) {
    int[] ints = {1, 2, 3, 4, 5,6,7,8,9,10};
    int i = searchInsertTwoDivider(ints, 11);
    System.out.println("i:" + i);
  }

  public static int searchInsert(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      if (target <= nums[i]) {
        return i;
      }
    }
    return nums.length;
  }

  public static int searchInsertTwoDivider(int[] nums, int target) {
    int start = 0, end = nums.length - 1;
    int mid=0;
    while (start <=end) {
      System.out.println("time");
      mid = start+(end - start) / 2;
      int midValue = nums[mid];
      if (midValue == target) {
        return mid;
      } else if (midValue > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return start+(end - start) / 2;
  }
}
