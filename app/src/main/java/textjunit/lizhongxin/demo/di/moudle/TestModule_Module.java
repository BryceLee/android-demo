package textjunit.lizhongxin.demo.di.moudle;

import dagger.Module;
import dagger.Provides;
import textjunit.lizhongxin.demo.di.A_Injector_Entity;
import textjunit.lizhongxin.demo.di.B_Module_Entity;

/**
 * Created by lizhongxin on 05/11/2018.
 */
@Module
public class TestModule_Module {

  @Provides
  B_Module_Entity providerB_Module() {
    return new B_Module_Entity();
  }
}
