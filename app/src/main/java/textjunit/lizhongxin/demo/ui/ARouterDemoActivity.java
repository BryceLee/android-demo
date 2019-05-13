package textjunit.lizhongxin.demo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import textjunit.lizhongxin.demo.R;


@Route(path = "/demo/a")
public class ARouterDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter_demo);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Postcard postcard = ARouter.getInstance().build("/bq/b").withLong("key1", 666L).withString("key2", "888");
                postcard
                        .navigation(ARouterDemoActivity.this,1);
//                Intent intent = new Intent(ARouterDemoActivity.this, textjunit.lizhongxin.bmodule.MainActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int back = data.getIntExtra("back", 0);
        Toast.makeText(this,"back:"+back,Toast.LENGTH_LONG).show();
    }
}
