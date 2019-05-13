package com.android.rxjavademo.ClassEntity;

import java.util.List;

public class GroupEntity {

  List<Student> studentList;

  public GroupEntity(List<Student> students) {
    this.studentList = students;
  }

  public List<Student> getStudents() {
    return studentList;
  }
}
