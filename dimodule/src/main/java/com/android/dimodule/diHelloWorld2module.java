package com.android.dimodule;

public class diHelloWorld2module {

  public static void main(String[] a) {
    helloModule();
  }

  private static void helloModule() {
    CarEntity_Module carEntityModule = new CarEntity_Module();
    carEntityModule.getWheelEntity().go();
    carEntityModule.getWheelEntity().go();
  }
}
