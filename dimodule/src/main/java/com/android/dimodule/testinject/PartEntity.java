package com.android.dimodule.testinject;


import javax.inject.Inject;

public class PartEntity {

  @Inject
  public PartEntity() {
    System.out.println("PartEntity init");
  }

  public void name() {
    System.out.println("i am a part");
  }
}
