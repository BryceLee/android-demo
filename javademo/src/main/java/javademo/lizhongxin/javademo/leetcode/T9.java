package javademo.lizhongxin.javademo.leetcode;

public class T9 {

  public static void main(String[] a) {
    boolean palindrome = isPalindrome(99111099);
    System.out.println("is=" + palindrome);
  }

  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    } else if (x < 10) {
      return true;
    } else {
      String s = x + "";
      int length = s.length();
      if (length % 2 == 0) {
        //偶数
        int mid = length / 2;
        int start = mid - 1, end = mid;
        boolean isTrue = true;
        while (start >= 0 && isTrue) {
          isTrue = s.substring(start, start + 1).equals(s.substring(end, end + 1));
          start--;
          end++;
        }
        return isTrue;
      } else {
        //奇数
        int mid = length / 2;
        int start = mid - 1, end = mid + 1;
        boolean isTrue = true;
        while (start >= 0 && isTrue) {
          isTrue = s.substring(start, start + 1).equals(s.substring(end, end + 1));
          start--;
          end++;
        }
        return isTrue;
      }
    }
  }
}
