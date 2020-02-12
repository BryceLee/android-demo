package textjunit.lizhongxin.demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import textjunit.lizhongxin.demo.R;

public class FastJsonActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fast_json);
    TextView mtv = findViewById(R.id.tv);
//    String s="{\"payment_num\":3.2}";
//    TestJson goodsDetailIntroEntity = JSON.parseObject(s, TestJson.class);
//    mtv.setText(goodsDetailIntroEntity.getPayment_num()+"");
  }
  public class TestJson {
    private int payment_num;


    public int getPayment_num() {
      return payment_num;
    }

    public void setPayment_num(int payment_num) {
      this.payment_num = payment_num;
    }
  }
}
