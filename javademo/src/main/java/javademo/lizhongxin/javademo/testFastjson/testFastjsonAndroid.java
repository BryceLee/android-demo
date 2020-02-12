package javademo.lizhongxin.javademo.testFastjson;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javademo.lizhongxin.javademo.testFastjson.testFastjson.ConverClass;

public class testFastjsonAndroid {

  public static void main(String[] a) {
    String s="{\"payment_num\":3.2}";
    TestJson goodsDetailIntroEntity = JSON.parseObject(s, TestJson.class);
    System.out.println(goodsDetailIntroEntity.payment_num);
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
