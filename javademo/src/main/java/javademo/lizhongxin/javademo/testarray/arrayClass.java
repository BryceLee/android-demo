package javademo.lizhongxin.javademo.testarray;

public class arrayClass {

  public static void main(String[] a) {
    int[] myarray = new int[10];
    String s = myarray.toString();
    System.out.println("array:" + s);
    System.out.println("array:hascode:" + System.identityHashCode(s));
    for (int i = 0; i < myarray.length; i++) {
      int v = myarray[i];
      System.out.println("arraym," + i + ":" + System.identityHashCode(v));
    }

  }
}
