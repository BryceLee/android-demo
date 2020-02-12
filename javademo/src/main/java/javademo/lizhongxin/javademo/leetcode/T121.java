package javademo.lizhongxin.javademo.leetcode;

public class T121 {

  public static void main(String[] a) {
    int[] prices = new int[]{7,6,4,3,1};
    int i = maxProfit(prices);
    System.out.println("i=" + i);
  }

  public static int maxProfit(int[] prices) {
    int left = prices[0], sum = 0;
    for (int i = 1; i < prices.length; i++) {
      if (left < prices[i]) {
        sum = Math.max(sum, prices[i] - left);
      } else {
        left = prices[i];
      }
    }
    return sum;
  }
}
