package com.android.dimodule;


public class WheelModuleEntity {
  private String name;

  public WheelModuleEntity(String name) {
    this.name=name;
    System.out.println("PartEntity init module2:"+name);
  }

  public void go() {
    System.out.println("PartEntity running module2:"+name);
  }
}
