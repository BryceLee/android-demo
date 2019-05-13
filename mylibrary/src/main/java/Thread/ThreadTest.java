package Thread;

import android.util.Log;
import javademo.lizhongxin.mylibrary.testjavademo.test;

/**
 * Created by lizhongxin on 18/01/2019.
 */

public class ThreadTest {

  private static int test = 1;
  private static String TAG = ThreadTest.class.getName();

  public static void main(String[] a) {
    for (int i = 0; i < 5; i++) {
      final int finalI = i;
      new Thread(new Runnable() {
        @Override
        public void run() {
          Log.d(TAG, "Ai:" + finalI + "test:" + test);
          test = test + 1;
          Log.d(TAG, "Bi:" + finalI + "test:" + test);
        }
      }).start();
    }
  }
}
