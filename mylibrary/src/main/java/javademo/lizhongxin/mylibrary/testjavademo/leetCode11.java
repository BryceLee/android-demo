package javademo.lizhongxin.mylibrary.testjavademo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongxin on 01/11/2018.
 */

public class leetCode11 {
    public static void main(String[] args) {
        int[] ints = {1, 1};
//        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int i = maxArea2(ints);
        System.out.println("answer:" + i);
    }

    static class mode {
        public int x;
        public int y;

        public mode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int maxArea(int[] height) {
//        List<mode> nums = new ArrayList<>();
//        for (int i = 1; i <= height.length; i++) {
//            nums.add(new mode(i,height[i-1]));
//        }
        int sum=0;
        int X;
        int Y;
        for (int i=0;i<height.length;i++){
            for (int j=i+1;j<height.length;j++){
                X=j-i;
                Y=(height[i]>height[j]?height[j]:height[i]);
                int all = X * Y;
                if (all>sum){
                    sum=all;

                }
            }
        }
        return sum;
    }
    public static int maxArea2(int[] height) {
        int sum=0;
        int n=height.length;//==2
        int leftX=0;
        int rightX=n-1;//1
        int X;
        int Y;
        //i是水池长度值
        for (int i=n-1;i>=1;i--){
            X=i;
            Y=Math.min(height[leftX],height[rightX]);
            sum=Math.max(sum,X*Y);
            if (Y==height[leftX]){
                //不要放过一个可能是大的数值
                leftX++;
            }else {
                rightX--;
            }
        }
        return sum;
    }
}
