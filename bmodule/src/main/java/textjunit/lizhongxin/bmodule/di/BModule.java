package textjunit.lizhongxin.bmodule.di;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lizhongxin on 05/11/2018.
 */
@Module
public class BModule {

  @Provides
  B getB() {
    return new B();
  }

}
