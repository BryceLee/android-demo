package textjunit.lizhongxin.demo.di.component;

import android.app.Activity;
import dagger.Component;
import textjunit.lizhongxin.bmodule.di.BModule;
import textjunit.lizhongxin.demo.di.DIActivity;
import textjunit.lizhongxin.demo.di.moudle.AModule;

/**
 * Created by lizhongxin on 05/11/2018.
 */
@Component(modules = {AModule.class, BModule.class})
public interface MainComponent {

  void intject(DIActivity activity);

}
