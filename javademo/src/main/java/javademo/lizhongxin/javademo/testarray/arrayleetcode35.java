package javademo.lizhongxin.javademo.testarray;

public class arrayleetcode35 {

  public static void main(String[] a) {
    int[] nums = new int[]{1,3,5,6};
    int len = searchInsert(nums, 2);
    System.out.println(len);
  }

  public static int searchInsert(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      if (target<=nums[i]){
        return i;
      }
    }
    return nums.length-1;
  }
}
