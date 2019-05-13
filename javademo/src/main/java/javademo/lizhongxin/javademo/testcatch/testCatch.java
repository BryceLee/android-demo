package javademo.lizhongxin.javademo.testcatch;

public class testCatch {
  public static void main(String[] a){
    String b=null;
    System.out.println("1");
    try {
      b.indexOf(1);
      System.out.println("2");
    }catch (Exception e){
      System.out.println("4");
    }
    System.out.println("5");
  }

}
