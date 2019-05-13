package com.android.rxjavademo;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class RxjavaFilter1 {

  public static void main(String[] a) {
    Observable.just("a","b","c","d","e","f","g").filter(new Predicate<String>() {
      @Override
      public boolean test(String s) throws Exception {
        System.out.println("test:"+s);
        return s.equals("a");
      }
    }).subscribe(new Consumer<String>() {
      @Override
      public void accept(String s) throws Exception {
        System.out.println(s);
      }
    });
  }

}
