package com.android.lanuchmode;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
    Log.d("Tag:","onCreate()"+getClassMessage());
  }

  @Override
  protected void onStart() {
    super.onStart();
    Log.d("Tag:","onStart()"+getClassMessage());
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d("Tag:","onResume()"+getClassMessage());
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d("Tag:","onRestart()"+getClassMessage());
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    Log.d("Tag:","onNewIntent()"+getClassMessage());
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d("Tag:","onPause()"+getClassMessage());
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d("Tag:","onStop()"+getClassMessage());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("Tag:","onDestroy()"+getClassMessage());
  }
  public String getClassMessage(){
    return " "+getClass();
  }
}
