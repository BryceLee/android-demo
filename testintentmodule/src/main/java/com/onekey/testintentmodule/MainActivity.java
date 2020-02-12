package com.onekey.testintentmodule;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //纯文字分享
//    Intent it = new Intent(Intent.ACTION_SEND);
//    it.putExtra(Intent.EXTRA_TEXT, "test");
//    it.setType("text/plain");
//    startActivity(Intent.createChooser(it, "分享文字"));
  }

  private void QQShare(String title) {
    ComponentName comp = new ComponentName("com.tencent.mobileqq",
        "com.tencent.mobileqq.activity.JumpActivity");
    Intent mulIntent = new Intent(Intent.ACTION_SEND);
    mulIntent.setType("text/plain");
    mulIntent.putExtra(Intent.EXTRA_TEXT, title);
    mulIntent.setComponent(comp);
    startActivity(Intent.createChooser(mulIntent, title));
  }

  private void WechatShare(String title) {
    ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
    Intent mulIntent = new Intent(Intent.ACTION_SEND);
    mulIntent.setType("text/plain");
    mulIntent.putExtra(Intent.EXTRA_TEXT, title);
    mulIntent.setComponent(comp);
    startActivity(Intent.createChooser(mulIntent, title));
  }

  private void CycleShare(String title) {
    //微信朋友圈，仅支持分享图片
    ComponentName comp = new ComponentName("com.tencent.mm",
        "com.tencent.mm.ui.tools.ShareToTimeLineUI");
    Intent mulIntent = new Intent(Intent.ACTION_SEND);
    mulIntent.setType("text/plain");
    mulIntent.putExtra(Intent.EXTRA_TEXT, title);
//    mulIntent.setType("text/*");
//    mulIntent.putExtra("Kdescription", title);
//    mulIntent.putExtra(Intent.EXTRA_STREAM, new ArrayList<>());
    mulIntent.setComponent(comp);
    startActivity(Intent.createChooser(mulIntent, title));
  }

  public void shareTocircle(View view) {
    CycleShare("test");
  }

  public void shareToWx(View view) {
    WechatShare("test");
  }

  public void shareToQQ(View view) {
    QQShare("test");
  }
}
