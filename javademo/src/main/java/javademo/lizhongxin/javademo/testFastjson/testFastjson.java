package javademo.lizhongxin.javademo.testFastjson;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class testFastjson {

  public static void main(String[] a) {
    Type superClassGenricType = getSuperClassGenricType(testM.class, 0);
    Object convert = new ConverClass(superClassGenricType).convert(null);
    System.out.println("c=" + convert);
  }


  public static class ConverClass<T> {

    private Type type;

    public ConverClass(Type type){

      this.type = type;
    }
    public T convert(String str) {
      T t = JSON.parseObject(str, type);
      return t;
    }
  }
  public static Type getSuperClassGenricType(final Class clazz, final int index) {
    //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
    Type genType = clazz.getGenericSuperclass();
    if (!(genType instanceof ParameterizedType)) {
      return Object.class;
    }
    //返回表示此类型实际类型参数的 Type 对象的数组。
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if (index >= params.length || index < 0) {
      return Object.class;
    }
    return params[index];
  }
}
