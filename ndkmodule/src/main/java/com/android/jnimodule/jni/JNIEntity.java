package com.android.jnimodule.jni;

public class JNIEntity {
  //这个类的包名和类名，静态方法必须和生成so的保持一致
  static {
    //对应so库的名字
    System.loadLibrary("lzxjni");
  }
  public static native  String helljni(String s);

}
