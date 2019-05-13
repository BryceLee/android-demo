package com.android.rxjavademo.ClassEntity;

import java.util.List;

public class ClassEntity {

  List<GroupEntity> mgroup;

  public ClassEntity(List<GroupEntity> mgroup) {
    this.mgroup = mgroup;
  }

  public List<GroupEntity> getGroupList() {
    return mgroup;
  }

  public void setMgroup(List<GroupEntity> mgroup) {
    this.mgroup = mgroup;
  }
}
