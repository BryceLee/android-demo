package com.android.modulerecycleview;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add("" + i);
    }
    RecyclerView rv = findViewById(R.id.rv);
    rv.setLayoutManager(
        new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    rv.setAdapter(new Myadapter(list));

  }

  public class Myadapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public Myadapter(
        @Nullable List<String> data) {
      super(R.layout.item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
      helper.setText(R.id.tv, item);
      Log.d("Myadapter",""+item);

    }
  }
}
