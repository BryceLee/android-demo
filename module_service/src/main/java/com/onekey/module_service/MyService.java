package com.onekey.module_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("MyService", "onStartCommand");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d("MyService", "onCreate");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d("MyService", "onDestroy");
  }
}
