package designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lizhongxin on 30/01/2019.
 */

public class ProxyClass {

  public static void main(String a[]) {
    CommonUploader commonUploader = new CommonUploader();
    //静态代理
    ProxyUploader proxyUploader = new ProxyUploader(commonUploader);
    proxyUploader.onUpload();
    //动态代理
    IUpload iUpload = (IUpload) Proxy.newProxyInstance(commonUploader.getClass().getClassLoader(),
        commonUploader.getClass().getInterfaces(), new DynamicProxyUploader(commonUploader));
    iUpload.onUpload();

  }

  interface IUpload {
    void onUpload();
  }

  public static class CommonUploader implements IUpload {

    @Override
    public void onUpload() {
      System.out.println("普通上传啦～");
    }
  }

  public static class ProxyUploader implements IUpload {

    CommonUploader commonUploader;

    private ProxyUploader(CommonUploader commonUploader) {
      this.commonUploader = commonUploader;
    }

    @Override
    public void onUpload() {
      System.out.println("上传前我先做点啥事～");
      commonUploader.onUpload();
      System.out.println("上传后我再做点啥事～");
    }
  }

  public static class DynamicProxyUploader implements InvocationHandler {

    Object object;

    DynamicProxyUploader(Object o) {
      this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("动态代理上传前我先做点啥事～");
      method.invoke(object, args);
      System.out.println("动态代理上传后我再做点啥事～");
      return null;
    }
  }
}
