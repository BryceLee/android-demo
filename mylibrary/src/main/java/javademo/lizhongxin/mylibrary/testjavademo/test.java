package javademo.lizhongxin.mylibrary.testjavademo;

import java.util.HashSet;
import javademo.lizhongxin.mylibrary.DemoClass;

/**
 * Created by lizhongxin on 08/09/2018.
 */

public class test {
    public static void main(String[] args) {
//        System.out.println(new DemoClass().toString());
//        System.out.println(new DemoClass().toString());
        testBreak();

    }
    public static void testBreak(){
        for (int i=0;i<8;i++){
            System.out.println("i"+i);
//            if (true){
//                break;
//            }
            System.out.println("xiix");
        }
    }
    public int lengthOfLongestSubstring(String allS) {
        int max = 1;
        int n = allS.length();
        for (int i = 0; i < n; i++) {
            for (int j=i+1;j<=n;j++){
                if (AllUnique(allS,i,j)){
                    max=Math.max(max,j-1);
                }
            }
        }
        return max;
    }

    public boolean AllUnique(String s, int start, int end) {
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = start; i < end; i++) {
            char single = s.charAt(i);
            if (hashSet.contains(single)) {
                return false;
            } else {
                hashSet.add(single);
            }
        }
        return true;
    }

    /**
     * 要不先找到各个字符在字符串里面有几个数字，String有方法可以找到字符在第几个位置
     */
}

