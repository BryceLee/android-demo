package com.onekey.multiprocessdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

  static int a = 1;//多进程，静态成员失效
  static String tag = "multiProcess";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    a++;
    Log.d(tag, this.getClass().getName() + "a=" + a);
  }

  public void jumpSecond(View view) {
    Intent intent = new Intent(this, SecondActivity.class);
    startActivity(intent);
  }
}
