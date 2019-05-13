package com.android.ndkmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.android.jnimodule.jni.JNIEntity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView tv = findViewById(R.id.tv);
    String helloJNI = JNIEntity.helljni("HelloJNI");
    tv.setText(helloJNI);
  }
}
