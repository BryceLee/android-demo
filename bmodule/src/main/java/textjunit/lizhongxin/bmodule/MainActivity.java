package textjunit.lizhongxin.bmodule;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/bq/b")
public class MainActivity extends AppCompatActivity {

    private static Activity activity;

    public static Activity getThis() {
        return activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainb);
        activity=this;
        long key1 = getIntent().getLongExtra("key1", 0);
        Log.d("key1",""+key1);

    }
    public void back(View view){
        Intent intent = new Intent();
        intent.putExtra("back",100);
        setResult(2,intent);
        finish();
    }
}
