package textjunit.lizhongxin.demo.di;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import javax.inject.Inject;
import textjunit.lizhongxin.demo.R;

public class DIActivity extends AppCompatActivity {

  @Inject
  B_Module_Entity b;
  //@Inject
  A_Injector_Entity a;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_di);
    //DaggerMainComponent.builder().build().intject(this);
//    DaggerMainComponent.builder().aModule(new TestModule_Module()).build()
  //      .intject(DIActivity.this);
//    Toast.makeText(this, "test==>" + a.toString() , Toast.LENGTH_SHORT)
//        .show();
    Log.d("di_test", a + "");
//    Toast.makeText(this, "test==>" + a.toString() + ",b:" + b.toString(), Toast.LENGTH_SHORT)
//        .show();
  }
}
