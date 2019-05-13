package javademo.lizhongxin.demo.di.moudle;

import dagger.Module;
import dagger.Provides;
import javademo.lizhongxin.demo.di.A;

/**
 * Created by lizhongxin on 05/11/2018.
 */
@Module
public class AModule {

  @Provides
  A getA() {
    return new A();
  }
}
