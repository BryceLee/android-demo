package com.android.rxjavademo;

import com.android.rxjavademo.ClassEntity.Man;
import com.android.rxjavademo.ClassEntity.Student;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxjavaSchudler4 {

  public static void main(String[] a) {
    //输入源是Student对象，进过map处理输出Man对象
    Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> e) throws Exception {
        System.out.println("Observable：" + getCurrentThread());
        e.onNext("hello schduler");
      }
    })
        .map(new Function<String, String>() {
          @Override
          public String apply(String s) throws Exception {
            return s + ",map1";
          }
        })
        .observeOn(Schedulers.newThread())
        .subscribe(
            new Observer<String>() {
              @Override
              public void onSubscribe(Disposable d) {
                System.out.println("订阅开始" + getCurrentThread());
              }

              @Override
              public void onNext(String value) {
                System.out.println("onNext:" + value + getCurrentThread());
              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onComplete() {
                System.out.println("onComplete:" + getCurrentThread());
              }
            }
        );
  }

  public static String getCurrentThread() {
    return "==>" + Thread.currentThread();
  }
}
