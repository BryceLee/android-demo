package com.android.rxjavademo.ClassEntity;

public class Student {

  int age;
  String name;

  public Student(String name) {
    this.name = name;
  }

  public Student(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
