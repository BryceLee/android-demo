package javademo.lizhongxin.javademo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestYYYY {

  public static void main(String[] s) {
    String property = System.getProperty("java.version");
    System.out.println("javaVersion=" + property);
    SimpleDateFormat YsimpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
    SimpleDateFormat ysimpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
    try {
      Date date = YsimpleDateFormat.parse("2020-01-01");
      System.out.println("data="+date);
      System.out.println("data="+ysimpleDateFormat.parse("2020-01-01"));
    } catch (ParseException e) {
      e.printStackTrace();
    }


  }
}
