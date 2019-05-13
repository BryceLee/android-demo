package com.android.dimodule.testinject;

public class diHelloInject {

  public static void main(String[] a) {
    helloworldInject();
  }


  private static void helloworldInject() {
    ComputerEntity carEntity = new ComputerEntity();
    PartEntity partEntity = carEntity.getPartEntity();
    partEntity.name();
  }
}
