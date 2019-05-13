package com.android.rxjavademo.ClassEntity;

import java.util.List;

public class SchoolEntity {

  List<ClassEntity> mClass;

  public SchoolEntity(List<ClassEntity> mClass) {
    this.mClass = mClass;
  }

  public List<ClassEntity> getClassList() {
    return mClass;
  }

  public void setMgroup(List<ClassEntity> mClass) {
    this.mClass = mClass;
  }
}
