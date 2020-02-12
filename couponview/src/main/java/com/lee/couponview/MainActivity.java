package com.lee.couponview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

  private boolean isselect;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final LeeCouponView couponView=findViewById(R.id.couponview);
    couponView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isselect){
          couponView.setBgcolor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
        }else {
          couponView.setBgcolor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));
        }
        isselect=!isselect;
      }
    });
  }
}
