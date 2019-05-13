package textjunit.lizhongxin.demo;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class ReflectActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reflect);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        isMain();
//                //1.加载Class对象
//                try {
//                    Class clazz = Class.forName("textjunit.lizhongxin.demo.mode.RMode");
//                    Constructor[] conArray = clazz.getConstructors();
//                    for (Constructor c : conArray) {
//                        System.out.println(c);
//                        Log.d("RP", c + "");
//                    }
//                    Field rname = clazz.getField("name");
//                    Log.d("RP_rname", rname.getName());
//                    String s = rname.get(clazz).toString();
//                    Log.d("RP_rname", s);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
      }
    });
  }

  public void isMain() {
    boolean b = Looper.myLooper() == Looper.getMainLooper();
    Log.d("bbb", b + "");
    new Thread(new Runnable() {
      @Override
      public void run() {
        boolean b = Looper.myLooper() == Looper.getMainLooper();
        Log.d("bbb", b + "");
      }
    }).start();
  }

}
