package com.android.rxjavademo;

import com.android.rxjavademo.ClassEntity.ClassEntity;
import com.android.rxjavademo.ClassEntity.GroupEntity;
import com.android.rxjavademo.ClassEntity.SchoolEntity;
import com.android.rxjavademo.ClassEntity.Student;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class RxjavaFlapMap3 {

  public static void main(String[] a) {
    Student xiahong = new Student("小红");
    Student xiaolan = new Student("小蓝");
    List<Student> students = new ArrayList<>();
    students.add(xiahong);
    students.add(xiaolan);
    students.add(new Student("小蓝2"));


    GroupEntity AGroup = new GroupEntity(students);
    Student xiaohuang = new Student("小黄");
    Student xiaolv = new Student("小绿");
    ArrayList<Student> students2 = new ArrayList<>();
    students2.add(xiaohuang);
    students2.add(xiaolv);
    GroupEntity BGroup = new GroupEntity(students2);

    List<GroupEntity> groupEntities = new ArrayList<>();
    groupEntities.add(AGroup);
    groupEntities.add(BGroup);
    ClassEntity classEntity = new ClassEntity(groupEntities);
    List<ClassEntity> list = new ArrayList<>();
    list.add(classEntity);
    SchoolEntity schoolEntity = new SchoolEntity(list);
    Observable.just(schoolEntity)
        .flatMap(new Function<SchoolEntity, ObservableSource<ClassEntity>>() {
          @Override
          public ObservableSource<ClassEntity> apply(SchoolEntity schoolEntity) throws Exception {
            //取出School中的班级组，遍历后得到班级对象
            return Observable.fromIterable(schoolEntity.getClassList());
          }
        }).flatMap(new Function<ClassEntity, ObservableSource<GroupEntity>>() {
      @Override
      public ObservableSource<GroupEntity> apply(ClassEntity classEntity) throws Exception {
        return Observable.fromIterable(classEntity.getGroupList());
      }
    }).flatMap(new Function<GroupEntity, ObservableSource<Student>>() {
      @Override
      public ObservableSource<Student> apply(GroupEntity groupEntity) throws Exception {
        return Observable.fromIterable(groupEntity.getStudents());
      }
    }).subscribe(new Consumer<Student>() {
      @Override
      public void accept(Student student) throws Exception {
        System.out.println("student->name:" + student.getName());
      }
    });
  }
}
