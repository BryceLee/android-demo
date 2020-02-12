package javademo.lizhongxin.javademo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class T349 {

  public static void main(String[] strings) {
    int[] a = new int[]{4, 9, 5};
    int[] b = new int[]{9, 4, 9, 8, 4};
    int[] c = intersection(a, b);
    for (int i = 0; i < c.length; i++) {
      System.out.println(c[i]);
    }

  }

  //时间复杂度nums1.length*nums2.length
  public static int[] intersection(int[] nums1, int[] nums2) {
    return hashMethod(nums1, nums2);
//    return nm(nums1, nums2);
  }

  private static int[] hashMethod(int[] nums1, int[] nums2) {
    HashSet<Integer> set1 = new HashSet<Integer>();
    for (int i = 0; i < nums1.length; i++) {
      set1.add(nums1[i]);
    }
    //System.out.println("set1,size="+set1.size());
    HashSet<Integer> set2 = new HashSet<Integer>();
    for (int i = 0; i < nums2.length; i++) {
      set2.add(nums2[i]);
    }
    //System.out.println("set2,size="+set2.size());
    if (set1.size() <= set2.size()) {
      return getInts(set1, set2);
    } else {
      return getInts(set2, set1);
    }
  }

  private static int[] getInts(HashSet<Integer> smallset, HashSet<Integer> bigset) {
    int[] ints = new int[smallset.size()];
    int s = 0;
    for (Integer i : smallset) {
      if (bigset.contains(i)) {
        ints[s++] = i;
      }
    }
    return Arrays.copyOf(ints,s);
  }

  private static int[] nm(int[] nums1, int[] nums2) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < nums1.length; i++) {
      for (int j = 0; j < nums2.length; j++) {
        if (nums1[i] == nums2[j]) {
          if (!list.contains(nums1[i])) {
            list.add(nums1[i]);
          }
        }
      }
    }
    int[] newA = new int[]{list.size()};
    for (int i = 0; i < newA.length; i++) {
      newA[i] = list.get(i);
    }
    return newA;
  }

}
