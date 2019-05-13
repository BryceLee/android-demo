package javademo.lizhongxin.javademo.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class streamdemo {

  private static char read;
  private static String readline;

  public static void main(String[] a) {
    System.out.println("输入行信息，输入done终止");
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    do {
      try {
        readline = bufferedReader.readLine();
        System.out.println(readline);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("read error");
      }
    } while (!readline.equals("done"));
  }
//  public static void main(String[] a) {
//    System.out.println("输出控制台的输入，按a终止");
//    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//    do {
//      try {
//        read = (char) bufferedReader.read();
//        System.out.println(read);
//      } catch (IOException e) {
//        e.printStackTrace();
//        System.out.println("read error");
//      }
//    } while (read != 'a');
//
//  }
}
