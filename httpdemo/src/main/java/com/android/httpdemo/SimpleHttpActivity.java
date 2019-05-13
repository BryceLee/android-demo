package com.android.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpActivity extends AppCompatActivity {

  TextView tv;
  String url="http://api.test.biaoqing.com//api/v3.0/feed";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.tv);
    tv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        httpRequst();
      }
    });
  }

  public void httpRequst() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            URL url = new URL("https://www.baidu.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            final int responseCode = urlConnection.getResponseCode();
            Log.d("myhttp", "responseCode:" + responseCode);
            InputStream inputStream = urlConnection.getInputStream();
            final StringBuffer stringBuffer = new StringBuffer();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            stringBuffer.append("responseCode:"+responseCode+"\n");
            while ((line = bufferedReader.readLine()) != null) {
              stringBuffer.append(line);
            }
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                tv.setText("" + stringBuffer.toString());
              }
            });
          } catch (final IOException e) {
            e.printStackTrace();
            Log.d("myhttp", "responseCode:" + e.toString());
            runOnUiThread(new Runnable() {
              @Override
              public void run() {
                tv.setText("" + e.toString());
              }
            });
          }
        }
      }).start();
    }
  }
}
