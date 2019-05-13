package javademo.lizhongxin.javademo.testarray;

public class arrayleetcode26 {

  public static void main(String[] a) {
    int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int len = removeDuplicates(nums);
    for (int i = 0; i < len; i++) {
      System.out.println(nums[i]);
    }
  }

  public static int removeDuplicates(int[] nums) {
    //快慢指针
    int slow = 0;
    for (int quick = 1; quick < nums.length; quick++) {
      if (nums[slow]==nums[quick]){
        //让quick自动加一就好了
      }else {
        //slow移动一位，让slow位=quick位
        slow++;
        nums[slow]=nums[quick];
      }
    }
    return ++slow;
  }

  public static int removeDuplicates2(int[] nums) {
    int len = nums.length;
    if (len <= 1) {
      return len;
    }
    int tail = 1;
    for (int i = 1; i < len; ++i) {
      if (nums[i - 1] != nums[i]) {
        nums[tail++] = nums[i];
      }
    }
    return tail;
  }
}
