package lzx.avdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import lzx.avdemo.MainActivity.MyAdapter.MyVideoHolder;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RecyclerView mRv = findViewById(R.id.rv);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
    linearLayoutManager
        .setOrientation(LinearLayoutManager.VERTICAL);
    mRv.setLayoutManager(linearLayoutManager);
    MyAdapter myAdapter = new MyAdapter();
    myAdapter.initData();
    mRv.setAdapter(myAdapter);
  }

  class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVideoHolder> {

    public List<String> mdata = new ArrayList<>();

    public void initData() {
      mdata.add("ijkdemo");
      mdata.add("录制视频demo");
    }

    class MyVideoHolder extends ViewHolder {

      public TextView mtv;

      public MyVideoHolder(View itemView) {
        super(itemView);
        mtv = itemView.findViewById(R.id.tvItem);

      }
    }

    @NonNull
    @Override
    public MyVideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View inflate = LayoutInflater.from(MainActivity.this)
          .inflate(R.layout.main_item, parent, false);
      return new MyVideoHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVideoHolder holder, int position) {
      String s = mdata.get(position);
      holder.mtv.setText(s);
      switch (position) {
        case 0: {
          Intent intent = new Intent(MainActivity.this, IjkActivity.class);
          startActivity(intent);
        }
        case 1:{
          Intent intent = new Intent(MainActivity.this, RecordActivity.class);
          startActivity(intent);
        }
        break;

      }
    }

    @Override
    public int getItemCount() {
      return mdata.size();
    }
  }
}
