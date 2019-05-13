package javademo.lizhongxin.javademo;

/**
 * Created by lizhongxin on 31/01/2019.
 */

public class SynchronizedDeadDemo {
  public static  int p=1;
  public static Object a = new Object();
  public static Object b = new Object();

  public static void main(final String[] a) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        synchronized (a){
          p++;
          System.out.println(Thread.currentThread().getName()+",p="+p);
        }
      }
    };
    Thread threadA = new Thread(runnable);
    Thread threadB = new Thread(runnable);
    Thread threadC = new Thread(runnable);
    Thread threadD = new Thread(runnable);
    threadA.start();
    threadB.start();
    threadC.start();
    threadD.start();
  }
}
