package javademo.lizhongxin.mylibrary.testjavademo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lizhongxin on 08/12/2018.
 */

public class leetcode15 {

  public static void main(String atg[]) {
    int[] res = {-1, 0, 1, 2, -1, -4};//-1 -1 0 1 2 4
    long start = System.currentTimeMillis();
    List<List<Integer>> lists = threeSum2(res);
    System.out.println("spendTime?" + (System.currentTimeMillis() - start));
    for (int i = 0; i < lists.size(); i++) {
      if (i == 0) {
        System.out.println("[");
      }
      List<Integer> integers = lists.get(i);
      for (int j = 0; j < integers.size(); j++) {
        if (j == 0) {
          System.out.print(" [");
        }
        System.out.print("" + integers.get(j) + ",");
        if (j == integers.size() - 1) {
          if (i == lists.size() - 1) {
            System.out.println("]");
          } else {
            System.out.println("],");
          }
        }
      }
      if (i == lists.size() - 1) {
        System.out.println("]");
      }
    }
//    Log.d("result", "");
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    //时间复杂度O(n^3)
    Arrays.sort(nums);
    ArrayList<List<Integer>> result = new ArrayList<>();
    //假设数组的元素数量大于三位
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> integers = new ArrayList<>();
            integers.add(nums[i]);
            integers.add(nums[j]);
            integers.add(nums[k]);
            if (!result.contains(integers)) {
              result.add(integers);
            }
          }
        }
      }
    }
    return result;
  }

  public static List<List<Integer>> threeSum2(int[] nums) {
    //双指针方案
    Arrays.sort(nums);
    ArrayList<List<Integer>> result = new ArrayList<>();
    //-1 -1 0 1 2 4
    //假设数组的元素数量大于三位
    int I=Integer.MAX_VALUE;
    for (int i = 0; i < nums.length - 2; i++) {
      if (I==nums[i]){
        continue;
      }
      I=nums[i];
      int start = i + 1;
      int end = nums.length - 1;
      while (start < end) {
        if (nums[start] + nums[end] + nums[i] == 0) {
          List<Integer> integers = new ArrayList<>();
          integers.add(nums[i]);
          integers.add(nums[start]);
          integers.add(nums[end]);
          result.add(integers);
          int right = nums[start];
          int rightBefore = nums[end];
          start++;
          end--;
          while (rightBefore == nums[end] && start < end ) {
            end--;
          }
          while (right == nums[start] && start < end) {
            start++;
          }
        } else if (nums[start] + nums[end] + nums[i] > 0) {
          end--;
        } else {
          start++;
        }
      }
    }
    return result;
  }

  public static List<List<Integer>> threeSum3(int[] nums) {
    Arrays.sort(nums);
    int iNum = Integer.MAX_VALUE;
    int jNum;
    int kNum;
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
      if (iNum == nums[i]) {
        continue;
      }
      iNum = nums[i];
      int start = i + 1;
      int end = nums.length - 1;
      while (start < end) {
        int sum = nums[i] + nums[start] + nums[end];
        if (sum > 0) {
          end--;
        } else if (sum < 0) {
          start++;
        } else {
          List<Integer> list = new ArrayList<>();
          list.add(nums[i]);
          list.add(nums[start]);
          list.add(nums[end]);
          result.add(list);

          jNum = nums[start];
          do {
            start++;
            if (start >= end) {
              break;
            }
          } while (jNum == nums[start]);
          kNum = nums[end];
          do {
            end--;
            if (start >= end) {
              break;
            }
          } while (kNum == nums[end]);
        }
      }
    }
    return result;
  }
}
