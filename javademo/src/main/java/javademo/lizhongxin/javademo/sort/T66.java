package javademo.lizhongxin.javademo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;

public class T66 {

  public static void main(String[] strings) {
    int[] a = new int[]{9, 9, 9};
    int[] c = plusOne(a);
    for (int i = 0; i < c.length; i++) {
      System.out.println(c[i]);
    }

  }

  //时间复杂度nums1.length*nums2.length
  public static int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i > -1; i--) {
      int b = digits[i];
      b += 1;
      if (b % 10 == 0) {
        //说明进位了
        digits[i] = 0;
      } else {
        //没有进位就结束了
        digits[i] = b;
        return digits;
      }
    }
    int[] c=new int[digits.length+1];
    c[0]=1;
    return c;
  }

}
