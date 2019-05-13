package com.android.jetpacklearning.databing;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.android.jetpacklearning.R;
import com.android.jetpacklearning.databinding.ActivityAcDataBingEasyBinding;

public class Ac_DataBing_Easy extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ac_data_bing_easy);
    ActivityAcDataBingEasyBinding viewDataBinding = DataBindingUtil
        .setContentView(this, R.layout.activity_ac_data_bing_easy);
    User user = new User("小A", "小B");
    viewDataBinding.setUser(user);
    viewDataBinding.topText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(Ac_DataBing_Easy.this, "topText", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
