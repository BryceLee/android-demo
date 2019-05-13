package javademo.lizhongxin.mylibrary;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by lizhongxin on 10/11/2018.
 */

public class rxjava {

  public static void main(String arg[]) {
    Flowable.just("Hello World").subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        System.out.println(s);
      }
    });
  }
}
