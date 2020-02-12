package javademo.lizhongxin.javademo.leetcode;

import android.icu.text.UFormat;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2 输出： 2 解释： 有两种方法可以爬到楼顶。 1.  1 阶 + 1 阶 2.  2 阶 示例 2：
 *
 * 输入： 3 输出： 3 解释： 有三种方法可以爬到楼顶。 1.  1 阶 + 1 阶 + 1 阶 2.  1 阶 + 2 阶 3.  2 阶 + 1 阶
 *
 * 输入： 4 输出： 3 解释： 有三种方法可以爬到楼顶。
 *
 * 1.  1 阶 + 1 阶 + 1 阶 +1 阶 2.  1 阶 + 1+2 阶 3.  1+2+1 4. 2+1+1 5. 2+2 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class T70 {

  public static void main(String[] s) {
    int allstep = 4;
    int[] tem = new int[allstep];
    System.out.println("size:"+tem.length);
    int i = climbStairs(0, allstep, tem);
    System.out.println(i + "种");
  }

  public static int climbStairs(int step, int n, int[] tem) {
    if (step > n) {
      return 0;
    }
    if (step == n) {
      return 1;
    }
    if (tem[step] > 0) {
      return tem[step];
    }
    tem[step] = climbStairs(step + 1, n, tem) + climbStairs(step + 2, n, tem);
    //System.out.println("step="+step);
    return tem[step];
  }
}
