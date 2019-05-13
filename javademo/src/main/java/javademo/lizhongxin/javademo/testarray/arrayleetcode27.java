package javademo.lizhongxin.javademo.testarray;

public class arrayleetcode27 {

  public static void main(String[] a) {
    int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int len = removeElement(nums, 2);
    for (int i = 0; i < len; i++) {
      System.out.println(nums[i]);
    }
  }

  public static int removeElement(int[] nums, int val) {
    int slow = 0;
    for (int i = 0; i < nums.length; ++i) {
      //如果快指针的数值不等于val，就把快指针的数值赋给慢指针的数值，慢指针，快指针都加一
      if (nums[i] != val) {
        nums[slow] = nums[i];
        slow++;
      }else {
        //如果慢指针不动，快指针➕1
      }
    }
    return slow;
  }
}
