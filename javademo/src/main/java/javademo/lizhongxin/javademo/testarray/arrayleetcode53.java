package javademo.lizhongxin.javademo.testarray;

public class arrayleetcode53 {

  public static void main(String[] a) {
    int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    int len = maxSubArray(nums);
    System.out.println(len);
  }

  public static int maxSubArray(int[] nums) {
    int max = nums[0];
    int tag = 0;
    for (int i = 0; i < nums.length; i++) {
      tag += nums[i];
      if (tag > max) {
        max = tag;
      }
    }
    return max;
  }
}
