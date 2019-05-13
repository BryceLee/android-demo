package textjunit.lizhongxin.demo.di;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import javax.inject.Inject;
import textjunit.lizhongxin.bmodule.di.B;
import textjunit.lizhongxin.bmodule.di.BModule;
import textjunit.lizhongxin.demo.R;
import textjunit.lizhongxin.demo.di.component.DaggerMainComponent;
import textjunit.lizhongxin.demo.di.moudle.AModule;

public class DIActivity extends AppCompatActivity {

  @Inject
  A a;
  @Inject
  B b;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_di);
    DaggerMainComponent.builder().aModule(new AModule()).bModule(new BModule()).build()
        .intject(DIActivity.this);
    Toast.makeText(this, "test==>" + a.toString() + ",b:" + b.toString(), Toast.LENGTH_SHORT)
        .show();
  }
}
