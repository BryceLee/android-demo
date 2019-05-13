package com.android.rxjavademo;

import com.android.rxjavademo.ClassEntity.Student;
import com.android.rxjavademo.ClassEntity.Man;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxjavaMap2 {

  public static void main(String[] a) {
    Student student = new Student(20);
    //输入源是Student对象，进过map处理输出Man对象
    Observable.just(student).map(new Function<Student, Man>() {
      @Override
      public Man apply(Student student) throws Exception {
        return new Man(student.getAge());
      }
    }).subscribe(new Consumer<Man>() {
      @Override
      public void accept(Man man) throws Exception {
        System.out.println("student->Man:" + man.getAge());
      }
    });
  }
}
