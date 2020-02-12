package com.onekey.module_service;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /**
     * 时间需要超过一分钟
     *     Process: com.onekey.module_service, PID: 24843
     *     java.lang.IllegalStateException: Not allowed to start service Intent { cmp=com.onekey.module_service/.MyService }: app is in background uid UidRecord{c696f48 u0a383 LAST bg:+1m0s524ms idle procs:1 seq(0,0,0)}
     *         at android.app.ContextImpl.startServiceCommon(ContextImpl.java:1515)
     *         at android.app.ContextImpl.startService(ContextImpl.java:1471)
     *         at android.content.ContextWrapper.startService(ContextWrapper.java:654)
     *         at com.onekey.module_service.MainActivity$1.run(MainActivity.java:19)
     *         at android.os.Handler.handleCallback(Handler.java:793)
     *         at android.os.Handler.dispatchMessage(Handler.java:98)
     *         at android.os.Looper.loop(Looper.java:173)
     *         at android.app.ActivityThread.main(ActivityThread.java:6698)
     *         at java.lang.reflect.Method.invoke(Native Method)
     *         at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240)
     *         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:782)
     */
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
      }
    },65000);
  }
}
