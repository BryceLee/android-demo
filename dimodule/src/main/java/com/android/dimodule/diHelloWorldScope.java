package com.android.dimodule;

public class diHelloWorldScope {

  public static void main(String[] a) {
    helloModule();
  }

  private static void helloModule() {
    //在diHelloWorld2module的基础上：想要实现轮子实体类的单例
    CarEntity_Module carEntityModule = new CarEntity_Module();
    carEntityModule.getWheelEntity().go();
    carEntityModule.getWheelEntity().go();
  }
}
