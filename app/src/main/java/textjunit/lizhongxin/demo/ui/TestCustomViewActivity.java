package textjunit.lizhongxin.demo.ui;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import textjunit.lizhongxin.demo.R;

public class TestCustomViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_custom_view);
    View iv = findViewById(R.id.iv);
    iv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(TestCustomViewActivity.this,TestAnimeActivity.class));
//        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(this,
//            R.anim.translate_in, R.anim.translate_none);
//        ActivityOptionsCompat compat = ActivityOptionsCompat
//            .makeSceneTransitionAnimation(TestCustomViewActivity.this, iv, "test");
//        ActivityCompat.startActivity(TestCustomViewActivity.this,
//            new Intent(TestCustomViewActivity.this, TestAnimeActivity.class), compat.toBundle());
      }
    });
  }
}
