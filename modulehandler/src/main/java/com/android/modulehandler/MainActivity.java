package com.android.modulehandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    Handler handler = new Handler() {
//      @Override
//      public void handleMessage(Message msg) {
//        super.handleMessage(msg);
//      }
//    };
//    Message obtain = Message.obtain();
//    obtain.arg1=1;
//    handler.sendMessage(obtain);
    final TextView textView = findViewById(R.id.tv);
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Looper.prepare();
            textView.setText("from thread looper");
      }
    }).start();

  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
