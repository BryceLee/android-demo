package com.android.lanuchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_a);
    TextView textView = findViewById(R.id.tv);
    textView.setText(getClass().getName()+",任务栈ID："+getTaskId());
    textView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(BActivity.this, CActivity.class);
        startActivity(intent);
      }
    });
  }
}
