package javademo.lizhongxin.javademo;

public class TestFor {

  public static void main(String[] a) {
//    testFor();
    int i = ~4;
    int max=Integer.MAX_VALUE+1;
    System.out.println("i="+i);
    System.out.println("i="+Integer.MAX_VALUE);
    System.out.println("max="+max);
  }

  private static void testFor() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          continue;
        }
        System.out.println("i=" + i + ",j=" + j);
      }
    }
  }

}
