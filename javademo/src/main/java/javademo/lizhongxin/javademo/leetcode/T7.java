package javademo.lizhongxin.javademo.leetcode;

public class T7 {

  public static void main(String[] a) {
    int reverse = reverse(123);
    System.out.println("r=" + reverse);
  }

  //-2^31个位是8,2^31-1个位是7
  public static int reverse(int x) {
    int ans = 0;
    int pop = 0;
    while (x != 0) {
      pop = x % 10;
      if ((ans > Integer.MAX_VALUE / 10) || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
        return 0;
      }
      if ((ans < Integer.MIN_VALUE / 10) || (ans == Integer.MIN_VALUE / 10) && pop < -8) {
        return 0;
      }
      ans = ans * 10 + pop;
      x /= 10;
    }
    return ans;
  }
}
