package javademo.lizhongxin.javademo.leetcode;

public class T122 {

  public static void main(String[] a) {
    int[] prices = new int[]{7, 1, 5, 100, 101};
    int i = maxProfit(prices);
    System.out.println("i=" + i);
  }

  public static int maxProfit(int[] prices) {
    int sum = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        sum += prices[i] - prices[i - 1];
      }
    }
    return sum;
  }

  public static int maxProfit2(int[] prices) {
    int maxprofit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        maxprofit += prices[i] - prices[i - 1];
      }
    }
    return maxprofit;
  }

}
