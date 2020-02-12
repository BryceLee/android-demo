package javademo.lizhongxin.javademo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class T3 {

  public static void main(String[] a) {
    int maxlength = lengthOfLongestSubstring2("abba");
    System.out.println("maxlength=" + maxlength);
  }

  public static int lengthOfLongestSubstring2(String s) {
    if (s.length() == 0) {
      return 0;
    }
    //把字符内容和位置做一个绑定，以便确定是否出现重复字符，一起出现的位置信息
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int max = 0;
    int left = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        left = Math.max(left, map.get(c) + 1);
      }
      //更新字符最后的位置
      map.put(c, i);
      max = Math.max(max, i + 1 - left);
    }
    return max;
  }

  public static int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) {
      return 0;
    }
    Set<Character> map = new HashSet<>();
    int repeattime = 0;
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.contains(c)) {
        repeattime++;
        max = Math.max(max, i - repeattime + 1);
      } else {
        map.add(c);
      }

    }
    return max;
  }

  public static int lengthOfLongestSubstring3(String s) {
    if (s.length() == 0) {
      return 0;
    }
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int length = 0, newstart = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      System.out.println("c=" + c);
      if (map.containsKey(c)) {
        length = Math.max(i - newstart, length);
        newstart = i;
        System.out.println("map包含" + ",i=" + i + ",newstart=" + newstart + ",length=" + length);
      } else {
        System.out.println("map不包含");
        length = Math.max(length, i - newstart + 1);
        System.out
            .println("map不包含,i=" + i + ",newstart=" + newstart + ",lengh=" + (i - newstart + 1));
      }
      map.put(c, i);//更新字符位置信息
    }
    return length;
  }
}
