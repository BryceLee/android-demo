package textjunit.lizhongxin.demo.di.component;

import dagger.Component;
import textjunit.lizhongxin.demo.di.DIActivity;
import textjunit.lizhongxin.demo.di.moudle.TestModule_Module;

/**
 * Created by lizhongxin on 05/11/2018.
 */
//@Component()
public interface InjectorComponent {

  void intject(DIActivity activity);

}
