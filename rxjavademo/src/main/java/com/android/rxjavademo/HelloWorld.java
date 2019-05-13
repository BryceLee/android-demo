package com.android.rxjavademo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HelloWorld {

  static int type = 3;
//  https://juejin.im/post/58aaaf1f8fd9c50067f2ae13#heading-11
  public static void main(String[] a) {
    switch (type) {
      case 1:
        HelloWorld();
        break;
      case 2:
        HelloWorldSecond();
        break;
      case 3:
        HelloWordThird();
        break;
    }
  }

  private static void HelloWorld() {
    Observable.just("hello world").subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        System.out.print(s);
      }
    });
  }

  private static void HelloWorldSecond() {
    Observable.just("hello world Complex").subscribe(new Observer<String>() {
      @Override
      public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        //可以取消
//        d.dispose();
      }

      @Override
      public void onNext(String value) {
        System.out.println("onNext：" + value);
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError");
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    });
  }

  private static void HelloWordThird() {
    Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> e) throws Exception {
        e.onNext("哈哈哈");
        e.onError(new Throwable("自定义错误信息"));
      }
    });
    stringObservable.subscribe(new Observer<String>() {
      @Override
      public void onSubscribe(Disposable d) {
        System.out.println("onSubscribe");
        //可以取消
//        d.dispose();
      }

      @Override
      public void onNext(String value) {
        System.out.println("onNext：" + value);
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError:"+e.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    });
  }
}
